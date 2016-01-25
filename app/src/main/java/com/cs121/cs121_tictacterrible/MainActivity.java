package com.cs121.cs121_tictacterrible;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    MultiplayerFragment multiplayerfragment = new MultiplayerFragment();
    SingleplayerFragment singleplayerfragment = new SingleplayerFragment();

    //evens are x turn
    //odds are o turn
    int turn = 0;

    //0 for no game going
    //1 for game going
    int game_status = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //Load default Page (multiplayer)
        android.support.v4.app.FragmentTransaction multiplayerfragmentTransaction = getSupportFragmentManager().beginTransaction();
        multiplayerfragmentTransaction.replace(R.id.fragment, multiplayerfragment);
        multiplayerfragmentTransaction.commit();
        //check multiplayer in navigation drawer by default
        navigationView.getMenu().findItem(R.id.multiplayer).setChecked(true);

    }

    public boolean ispressedblank(View v) {
        int i = v.getId();
        ImageButton vv = (ImageButton) v;
        if (v.getTag().equals("X") || v.getTag().equals("O")) {
            return false;
        }
        return true;
    }

    public int whoseturn() {
        return turn % 2;
        //if returns 0, then x turn
        //if return 1, then o turn
    }

    //X: ic_menu_gallery
    //O: ic_menu_send
    public void clickBoard(View v) {
        if (ispressedblank(v) && game_status == 1) {
            ImageButton vv = (ImageButton) v;
            if (whoseturn() == 0) {
                //x's turn
                v.setTag("X");
                vv.setImageResource(R.drawable.ic_menu_gallery);
                if (checkifwin()) {
                    gameover(v, 0);
                }
            } else {
                //o's turn
                v.setTag("O");
                vv.setImageResource(R.drawable.ic_menu_send);
                if (checkifwin()) {
                    gameover(v, 1);
                }
            }

            //adds a turn
            turn++;
        }
        //If there have been 9 turns, tie game
        if (turn == 9 && game_status == 1) {
            gameover(v, 2);
        }
    }

    public boolean checkifwin() {
        ImageButton a = (ImageButton) findViewById(R.id.imageButton00);
        ImageButton b = (ImageButton) findViewById(R.id.imageButton10);
        ImageButton c = (ImageButton) findViewById(R.id.imageButton20);
        ImageButton d = (ImageButton) findViewById(R.id.imageButton01);
        ImageButton e = (ImageButton) findViewById(R.id.imageButton11);
        ImageButton f = (ImageButton) findViewById(R.id.imageButton21);
        ImageButton g = (ImageButton) findViewById(R.id.imageButton02);
        ImageButton h = (ImageButton) findViewById(R.id.imageButton12);
        ImageButton i = (ImageButton) findViewById(R.id.imageButton22);

        //3 in a column
        if ((a.getTag().equals(b.getTag()) && (b.getTag().equals(c.getTag()))) && ((a.getTag().equals("X")) || (a.getTag().equals("O")))) {
            a.setBackgroundColor(getResources().getColor(R.color.winhighlight));
            b.setBackgroundColor(getResources().getColor(R.color.winhighlight));
            c.setBackgroundColor(getResources().getColor(R.color.winhighlight));
            return true;
        }
        if ((d.getTag().equals(e.getTag()) && (e.getTag().equals(f.getTag()))) && ((d.getTag().equals("X")) || (d.getTag().equals("O")))) {
            d.setBackgroundColor(getResources().getColor(R.color.winhighlight));
            e.setBackgroundColor(getResources().getColor(R.color.winhighlight));
            f.setBackgroundColor(getResources().getColor(R.color.winhighlight));
            return true;
        }
        if ((g.getTag().equals(h.getTag()) && (h.getTag().equals(i.getTag()))) && ((g.getTag().equals("X")) || (g.getTag().equals("O")))) {
            g.setBackgroundColor(getResources().getColor(R.color.winhighlight));
            h.setBackgroundColor(getResources().getColor(R.color.winhighlight));
            i.setBackgroundColor(getResources().getColor(R.color.winhighlight));
            return true;
        }
        //3 in a row
        if ((a.getTag().equals(d.getTag()) && (d.getTag().equals(g.getTag()))) && ((a.getTag().equals("X")) || (a.getTag().equals("O")))) {
            a.setBackgroundColor(getResources().getColor(R.color.winhighlight));
            d.setBackgroundColor(getResources().getColor(R.color.winhighlight));
            g.setBackgroundColor(getResources().getColor(R.color.winhighlight));
            return true;
        }
        if ((b.getTag().equals(e.getTag()) && (e.getTag().equals(h.getTag()))) && ((b.getTag().equals("X")) || (b.getTag().equals("O")))) {
            b.setBackgroundColor(getResources().getColor(R.color.winhighlight));
            e.setBackgroundColor(getResources().getColor(R.color.winhighlight));
            h.setBackgroundColor(getResources().getColor(R.color.winhighlight));
            return true;
        }
        if ((c.getTag().equals(f.getTag()) && (f.getTag().equals(i.getTag()))) && ((c.getTag().equals("X")) || (c.getTag().equals("O")))) {
            c.setBackgroundColor(getResources().getColor(R.color.winhighlight));
            f.setBackgroundColor(getResources().getColor(R.color.winhighlight));
            i.setBackgroundColor(getResources().getColor(R.color.winhighlight));
            return true;
        }
        //3 in a diagnal
        if ((a.getTag().equals(e.getTag()) && (e.getTag().equals(i.getTag()))) && ((a.getTag().equals("X")) || (a.getTag().equals("O")))) {
            a.setBackgroundColor(getResources().getColor(R.color.winhighlight));
            e.setBackgroundColor(getResources().getColor(R.color.winhighlight));
            i.setBackgroundColor(getResources().getColor(R.color.winhighlight));
            return true;
        }
        if ((c.getTag().equals(e.getTag()) && (e.getTag().equals(g.getTag()))) && ((c.getTag().equals("X")) || (c.getTag().equals("O")))) {
            c.setBackgroundColor(getResources().getColor(R.color.winhighlight));
            e.setBackgroundColor(getResources().getColor(R.color.winhighlight));
            g.setBackgroundColor(getResources().getColor(R.color.winhighlight));
            return true;
        }
        return false;
    }

    public void gameover(View v, int winner) {
        if (winner == 0) {
            ((TextView) findViewById(R.id.multitextviewmessage)).setText("X WON!");
        } else if (winner == 1) {
            ((TextView) findViewById(R.id.multitextviewmessage)).setText("O WON!");
        } else if (winner == 2) {
            ((TextView) findViewById(R.id.multitextviewmessage)).setText("TIE");
        }
        game_status = 0;
    }

    public void newgame(View v) {
        turn = 0;
        ((TextView) findViewById(R.id.multitextviewmessage)).setText("");

        //get views
        ImageButton a = (ImageButton) findViewById(R.id.imageButton00);
        ImageButton b = (ImageButton) findViewById(R.id.imageButton10);
        ImageButton c = (ImageButton) findViewById(R.id.imageButton20);
        ImageButton d = (ImageButton) findViewById(R.id.imageButton01);
        ImageButton e = (ImageButton) findViewById(R.id.imageButton11);
        ImageButton f = (ImageButton) findViewById(R.id.imageButton21);
        ImageButton g = (ImageButton) findViewById(R.id.imageButton02);
        ImageButton h = (ImageButton) findViewById(R.id.imageButton12);
        ImageButton i = (ImageButton) findViewById(R.id.imageButton22);

        //clear tags
        a.setTag("00");
        b.setTag("10");
        c.setTag("20");
        d.setTag("01");
        e.setTag("11");
        f.setTag("21");
        g.setTag("02");
        h.setTag("12");
        i.setTag("22");

        //Set tiles back to blank
        a.setImageResource(android.R.color.transparent);
        b.setImageResource(android.R.color.transparent);
        c.setImageResource(android.R.color.transparent);
        d.setImageResource(android.R.color.transparent);
        e.setImageResource(android.R.color.transparent);
        f.setImageResource(android.R.color.transparent);
        g.setImageResource(android.R.color.transparent);
        h.setImageResource(android.R.color.transparent);
        i.setImageResource(android.R.color.transparent);

        //reset background colors
        a.setBackgroundColor(getResources().getColor(R.color.defaultbackground));
        b.setBackgroundColor(getResources().getColor(R.color.defaultbackground));
        c.setBackgroundColor(getResources().getColor(R.color.defaultbackground));
        d.setBackgroundColor(getResources().getColor(R.color.defaultbackground));
        e.setBackgroundColor(getResources().getColor(R.color.defaultbackground));
        f.setBackgroundColor(getResources().getColor(R.color.defaultbackground));
        g.setBackgroundColor(getResources().getColor(R.color.defaultbackground));
        h.setBackgroundColor(getResources().getColor(R.color.defaultbackground));
        i.setBackgroundColor(getResources().getColor(R.color.defaultbackground));


        game_status = 1;
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.multiplayer) {
            android.support.v4.app.FragmentTransaction multiplayerfragmentTransaction = getSupportFragmentManager().beginTransaction();
            multiplayerfragmentTransaction.replace(R.id.fragment, multiplayerfragment);
            multiplayerfragmentTransaction.commit();
        } else if (id == R.id.singleplayer) {
            android.support.v4.app.FragmentTransaction singleplayerfragmentTransaction = getSupportFragmentManager().beginTransaction();
            singleplayerfragmentTransaction.replace(R.id.fragment, singleplayerfragment);
            singleplayerfragmentTransaction.commit();
        } else if (id == R.id.highscores) {

        } else if (id == R.id.settings) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
