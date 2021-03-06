package ch.bbcag.wynncraftstatistics.Activities.HomeScreen;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import ch.bbcag.wynncraftstatistics.JSON.PlayerStatsFetcher;
import ch.bbcag.wynncraftstatistics.Player.Player;
import ch.bbcag.wynncraftstatistics.Player.PlayerStatsHolder;
import ch.bbcag.wynncraftstatistics.R;

/**
 * Created by zdomaa on 19.06.2015.
 */
public class HomeFragment extends Fragment {
    private ProgressDialog mDialog;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {

        PlayerStatsHolder holder;
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        Player user = new Player();
        if(getActivity().getIntent().getStringExtra("mode").equals("ownName")){
            user.setPlayerName(this.getActivity().getIntent().getStringExtra("username"));
        } else if (getActivity().getIntent().getStringExtra("mode").equals("friendName")){
            user.setPlayerName(getActivity().getIntent().getStringExtra("friendName"));
        }


        ImageView playerImage = (ImageView) rootView.findViewById(R.id.userIcon);
        user.loadPlayerIcon(playerImage, 256);

        TextView username = (TextView) rootView.findViewById(R.id.username);
        username.setText(user.getPlayerName());

        holder = new PlayerStatsHolder (
                (ImageView) rootView.findViewById(R.id.userIcon),
                (TextView) rootView.findViewById(R.id.rank),
                (TextView) rootView.findViewById(R.id.playtimeText),
                (TextView) rootView.findViewById(R.id.totallevelText),
                (TextView) rootView.findViewById(R.id.mageLabel1),
                (TextView) rootView.findViewById(R.id.mageLabel2),
                (TextView) rootView.findViewById(R.id.archerLabel1),
                (TextView) rootView.findViewById(R.id.archerLabel2),
                (TextView) rootView.findViewById(R.id.warriorLabel1),
                (TextView) rootView.findViewById(R.id.warriorLabel2),
                (TextView) rootView.findViewById(R.id.assassinLabel1),
                (TextView) rootView.findViewById(R.id.assassinLabel2)
        );
        mDialog = ProgressDialog.show(getActivity(), getString(R.string.loading), getString(R.string.wait));
        new PlayerStatsFetcher(mDialog,getActivity().getApplicationContext(),
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE), getActivity(),holder ).execute(user.getPlayerName());
        return rootView;

    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.empty_menu, menu);
    }
}
