package dk.miracle.grapevine.realm_db;

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import dk.miracle.grapevine.news.Channel;
import dk.miracle.grapevine.news.DatabaseUpdatedListener;
import dk.miracle.grapevine.news.MediaContent;
import dk.miracle.grapevine.news.News;
import dk.miracle.grapevine.realm_db.model.ChannelEntity;
import dk.miracle.grapevine.realm_db.model.CommentEntity;
import dk.miracle.grapevine.realm_db.model.LikeEntity;
import dk.miracle.grapevine.realm_db.model.MediaContentEntity;
import dk.miracle.grapevine.realm_db.model.NewsEntity;
import hugo.weaving.DebugLog;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by mak on 12-01-2016.
 */

public class NewsDAO {
    private static final String TAG = NewsDAO.class.getSimpleName();
    private final DatabaseUpdatedListener databaseUpdateListener;
    private Realm transactionUI;

    @DebugLog
    public NewsDAO(DatabaseUpdatedListener databaseUpdateListener) {
        this.databaseUpdateListener = databaseUpdateListener;
        transactionUI = Realm.getDefaultInstance();
    }

    @DebugLog
    public RealmResults<NewsEntity> getAllNewsByChannel(int channelId) {
        return transactionUI.where(NewsEntity.class)
                .equalTo("channelId", channelId)
                .findAllSorted("created", Sort.DESCENDING);
    }


    @DebugLog
    public NewsEntity getSpecificNews(Realm transaction, int newsId) {
        return transaction.where(NewsEntity.class)
                .equalTo("id", newsId)
                .findFirst();
    }

    @DebugLog
    public String getChannelName(int channelId) {
        ChannelEntity result = transactionUI.where(ChannelEntity.class)
                .equalTo("channelId", channelId)
                .findFirst();
        return result != null ? result.getName() : "";
    }

    @DebugLog
    public void persistNews(final News news) {
        Realm localTransAction = Realm.getDefaultInstance();
        if (news.isAComment()) {
            localTransAction.beginTransaction();
            getSpecificNews(localTransAction, news.getParentId()).getCommentsList().add(createCommentEntity(news));
            localTransAction.commitTransaction();
        } else {
            createNewsObject(news, localTransAction);
        }
        localTransAction.close();
        databaseUpdateListener.onDatabaseUpdated();
    }

    public void persistNews(final List<News> list) {
        Log.d(TAG, "persistNews() list.size: " + list.size());
        new AsyncTask<Void, Void, Void>() {
            private List<News> localList = list;

            @Override
            protected Void doInBackground(Void... params) {
                Realm localTransAction = Realm.getDefaultInstance();
                for (News news : localList) {
                    if (!news.isAComment()) {
                        createNewsObject(news, localTransAction);
                    }
                }
                localTransAction.close();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                databaseUpdateListener.onDatabaseUpdated();
            }
        }.execute();
    }

    private NewsEntity createNewsObject(News news, Realm localTransAction) {
        localTransAction.beginTransaction();
        NewsEntity newsEntity = createNewsRealm(news);
        RealmList<LikeEntity> likeList = new RealmList<>();
        if (news.getLikedByUserIds() != null && !news.getLikedByUserIds().isEmpty()) {
            for (int item : news.getLikedByUserIds()) {
                likeList.add(new LikeEntity(item));
            }
            newsEntity.setLikedByUserIds(likeList);
        }
        newsEntity = localTransAction.copyToRealmOrUpdate(newsEntity);
        localTransAction.commitTransaction();
        if (!news.getCommentsList().isEmpty()) {
            localTransAction.beginTransaction();
            for (News comment : news.getCommentsList()) {
                newsEntity.getCommentsList().add(createCommentEntity(comment));
            }
            localTransAction.commitTransaction();
        }
        if (news.hasChannel()) {
            persistChannel(news.getChannel(), localTransAction);
        }
        if (news.hasMediaContent()) {
            persistMediaContent(newsEntity, news, localTransAction);
        } else if (news.isDeleteMediaContent()) {
            deleteMediaContent(localTransAction, news.getAuthorId());
        }
        return newsEntity;
    }

    private void deleteMediaContent(Realm localTransAction, int ownerId) {
        MediaContentEntity mediaContentEntity = localTransAction
                .where(MediaContentEntity.class)
                .equalTo("ownedById", ownerId)
                .findFirst();

        if (mediaContentEntity != null) {
            localTransAction.beginTransaction();
            mediaContentEntity.removeFromRealm();
            localTransAction.commitTransaction();
        }
    }

    private void persistMediaContent(NewsEntity newsEntity, News news, Realm localTransAction) {

        localTransAction.beginTransaction();
        MediaContentEntity mediaContentEntity = localTransAction.copyToRealmOrUpdate(createMediaContentEntity(news.getMediaContent()));
        localTransAction.commitTransaction();
        localTransAction.beginTransaction();
        newsEntity.setMediaContentEntity(mediaContentEntity);
        localTransAction.commitTransaction();
    }

    private CommentEntity createCommentEntity(News from) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setParentId(from.getParentId());
        commentEntity.setId(from.getId());
        commentEntity.setAuthorId(from.getAuthorId());
        commentEntity.setAuthorName(from.getAuthorName());
        commentEntity.setAuthorPictureUrl(from.getAuthorPictureUrl());
        commentEntity.setAuthorPictureUrl(from.getAuthorPictureUrl());
        commentEntity.setBody(from.getBody());
        commentEntity.setCreated(from.getCreated());
        return commentEntity;
    }

    private MediaContentEntity createMediaContentEntity(MediaContent mediaContent) {
        MediaContentEntity mediaContentEntity = new MediaContentEntity();
        mediaContentEntity.setId(mediaContent.getId());
        mediaContentEntity.setContentUrl(mediaContent.getContentUrl());
        mediaContentEntity.setThumbnailUrl(mediaContent.getThumbnailUrl());
        mediaContentEntity.setHeight(mediaContent.getHeight());
        mediaContentEntity.setWidth(mediaContent.getWidth());
        mediaContentEntity.setMimeType(mediaContent.getMimeType());
        return mediaContentEntity;
    }

    private void persistChannel(Channel channel, Realm localTransAction) {
        localTransAction.beginTransaction();
        localTransAction.copyToRealmOrUpdate(createChannelRealm(channel));
        localTransAction.commitTransaction();
    }


    private ChannelEntity createChannelRealm(Channel channel) {
        ChannelEntity nChannel = new ChannelEntity();
        nChannel.setChannelId(channel.getChannelId());
        nChannel.setName(channel.getName());
        return nChannel;
    }

    private NewsEntity createNewsRealm(News from) {

        NewsEntity to = new NewsEntity();
        if (from.hasChannel()) {
            to.setChannelId(from.getChannel().getChannelId());
        }
        to.setDeleteMediaContent(from.isDeleteMediaContent());
        to.setId(from.getId());
        to.setAuthorId(from.getAuthorId());
        to.setAuthorName(from.getAuthorName());
        to.setAuthorPictureUrl(from.getAuthorPictureUrl());
        to.setBody(from.getBody());
        to.setCreated(from.getCreated());
        if (from.getServiceAnnouncementEnd() != null) {
            to.setServiceAnnouncementEnd(from.getServiceAnnouncementEnd());
            to.setServiceAnnouncementStart(from.getServiceAnnouncementStart());
        }
        to.setTitle(from.getTitle());
        return to;
    }

    private void beginTransaction() {
        transactionUI.beginTransaction();
    }

    private void commitTransaction() {
        transactionUI.commitTransaction();
    }

    public Realm getTransactionUI() {
        return transactionUI;
    }

    public void getCloseTransaction() {
        transactionUI.close();
    }

}
