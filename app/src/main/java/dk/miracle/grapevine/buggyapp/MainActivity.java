package dk.miracle.grapevine.buggyapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import dk.miracle.grapevine.news.DatabaseUpdatedListener;
import dk.miracle.grapevine.news.News;
import dk.miracle.grapevine.realm_db.NewsDAO;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity {

    private Realm realm;
    private RealmConfiguration realmConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create the Realm configuration
        realmConfig = new RealmConfiguration.Builder(this).build();
        // Open the Realm for the UI thread.
        realm = Realm.getInstance(realmConfig);
        Realm.setDefaultConfiguration(realmConfig);

        final NewsDAO dao = new NewsDAO(new DatabaseUpdatedListener() {
            @Override
            public void onDatabaseUpdated() {
                Log.d("NewsDAO", "Database has updated");
            }
        });

        final int newsCount = 100;
        final ArrayList<News> pseudoNewsList = new ArrayList<News>();
        for ( int i = 0; i < newsCount; ++i) {
            pseudoNewsList.add(News.getNews());
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dao.persistNews(pseudoNewsList);
                }
            }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

}
