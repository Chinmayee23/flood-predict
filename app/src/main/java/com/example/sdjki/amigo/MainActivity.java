package com.example.sdjki.amigo;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.sdjki.amigo.doc;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Source;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, SwipeRefreshLayout.OnRefreshListener{
    private Toolbar mToolbar;

    FirebaseFirestore db=FirebaseFirestore.getInstance();
    CollectionReference brandsCollectionRef=db.collection("flood");
    private DrawerLayout mDrawerLayout;
    private static final String TAG = "MainActivity";
    private FirebaseFirestore db1 = FirebaseFirestore.getInstance();
    private DocumentReference ref = db1.collection("Fashion").document("KSr1HWAX6oi5454dQbKQ").collection("Sports").document("Adidas");
    private String img;
    private  ArrayList<doc>docu1=new ArrayList<>();
    private ImageView image;
    private RecyclerView mRecyclerView;
    RecyclerViewAdapter mRecyclerViewAdapter;
    private DocumentSnapshot mLastQueriedDocument;
    private ArrayList<String>mDates=new ArrayList<>();
    private ArrayList<String>mHumidity=new ArrayList<>();
    private ArrayList<String>mprecip=new ArrayList<>();
    private ArrayList<String>mpressure=new ArrayList<>();
    private ArrayList<String>mtemp=new ArrayList<>();
    private ArrayList<String>mwind=new ArrayList<>();
    private ArrayList<String>mflood=new ArrayList<>();
    private ArrayList<String>mNames=new ArrayList<>();
    private ArrayList<String>mImageUrls=new ArrayList<>();
    private ArrayList<doc>mdoc=new ArrayList<>();
    private SwipeRefreshLayout mSwipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSwipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        setupToolbarMenu();
        //setupNavigationDrawerMenu();
        mSwipeRefreshLayout.setOnRefreshListener(this);
        initRecyclerView();
        getBrands();
    }

    private void setupToolbarMenu() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("Flood prediction App");
    }


    private void setupNavigationDrawerMenu() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationView);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,
                mDrawerLayout,
                mToolbar,
                R.string.drawer_open,
                R.string.drawer_close
        );
        mDrawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

    }

    private void closeDrawer() {
        mDrawerLayout.closeDrawer(GravityCompat.START);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START))
            closeDrawer();
        else
            super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_food:
                break;
            case R.id.item_fashion:
                break;
            case R.id.item_beauty:
                Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_entertainment:
                Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_home:
                Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_tech:
                Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_gifts:
                Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_jewellery:
                Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_sports:
                Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_books:
                Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
                break;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    private void initImageBitmaps(){
        Log.d(TAG,"initImageBitmaps: preparing bitmaps");

    }
    private void initRecyclerView(){
        Log.d(TAG,"initRecyclerView: init recyclerview");
        RecyclerView recyclerView=findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter=new RecyclerViewAdapter(this,mNames,mImageUrls,mHumidity,mprecip,mpressure,mtemp,mwind,mflood,docu1);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    private void getBrands(){
        Query brandsQuery=brandsCollectionRef;
        brandsQuery.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                        for (QueryDocumentSnapshot document: task.getResult()){
                            doc docu1=document.toObject(doc.class);
                            mNames.add(docu1.getDate());
                            mImageUrls.add(docu1.getprecip());
                            mHumidity.add(docu1.getHumidity());
                            mprecip.add(docu1.getprecip());
                            mpressure.add(docu1.getpressure());
                            mtemp.add(docu1.gettemp());
                            mwind.add(docu1.getwind());
                            mflood.add(docu1.getflood());
                        }

                    initRecyclerView();

                }
                else {
                    Toast.makeText(MainActivity.this, "Query failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onRefresh() {
        mNames.clear();
        mwind.clear();
        mtemp.clear();
        mprecip.clear();
        mpressure.clear();
        mwind.clear();
        mHumidity.clear();
        mflood.clear();
        mImageUrls.clear();
        docu1.clear();
        getBrands();
        mSwipeRefreshLayout.setRefreshing(false);
    }

}
