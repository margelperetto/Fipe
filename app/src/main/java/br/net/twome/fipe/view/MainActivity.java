package br.net.twome.fipe.view;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import br.net.twome.fipe.R;
import br.net.twome.fipe.utils.Android;

public class MainActivity extends AppCompatActivity implements MaterialSearchView.OnQueryTextListener{

    private MaterialSearchView searchView;
    private AbstractFragment selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(this);

        Log.d("SAVED","É nulo? "+(savedInstanceState==null));

        if(selected == null){
            showFragment(FragmentTipo.newInstance());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        MenuItem item = menu.findItem(R.id.menu_search);
        searchView.setMenuItem(item);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_search: {

                return true;
            }
            default: return false;
        }
    }

    @Override
    public void onBackPressed() {
        if (fecharPesquisa()) {
            return;
        }
        if(selected!=null){
            AbstractFragment ant = selected.fragmentAnterior();
            if(ant!=null){
                showFragment(ant);
                return;
            }
        }
        Android.finishDialog(this);
    }

    public boolean fecharPesquisa(){
        if (searchView.isSearchOpen()) {
            searchView.closeSearch();
            return true;
        }
        return false;
    }

    public void showFragment(AbstractFragment fragment) {
        fecharPesquisa();
        this.selected = fragment;
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_body, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if(selected!=null && newText!=null && !newText.isEmpty()){
            selected.orderList(newText);
            return true;
        }
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) { return false; }

}
