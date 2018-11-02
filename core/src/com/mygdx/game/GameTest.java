package com.mygdx.game;

import com.badlogic.gdx.Game;

public class GameTest extends Game {

	@Override
	public void create () {
		setScreen(new PlayScreen(this));
	}
}
