package com.example.dungeonescape.activities;

import android.content.Context;
import android.content.Intent;

import com.example.dungeonescape.player.Player;
import com.example.dungeonescape.player.PlayerManager;

public class MenuActivity extends GeneralGameActivity {

    public MenuActivity(){}

    public Intent createIntent(Context packageContext,
                     Class nextClass,
                     PlayerManager playerManager,
                     Player player) {
        Intent intent = new Intent(packageContext, nextClass);
        intent.putExtra("Player", player);
        intent.putExtra("Game Manager", playerManager);

        return intent;
    }

}