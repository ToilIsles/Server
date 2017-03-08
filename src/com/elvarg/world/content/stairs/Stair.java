package com.elvarg.world.content.stairs;

import com.elvarg.world.model.Position;

public class Stair {
	private Position pos;
	private Position playerPos;
	private int id;
	private int playerFace;
	private int animation;

	public Stair(int id, Position pos, Position playerPos) {
		this.pos = pos;
		this.playerPos = playerPos;
		this.id = id;
		this.animation = -1;
		this.playerFace = -1;
	}

	public Stair setAnimation(int anim) {
		animation = anim;
		return this;
	}

	public Stair setPlayerFace(int face) {
		playerFace = face;
		return this;
	}

	public Position getPosition() {
		return pos;
	}

	public Position getPlayerTelePos() {
		return playerPos;
	}

	public int getId() {
		return id;
	}

	public int getAnimation() {
		return animation;
	}

	public int getPlayerFace() {
		return playerFace;
	}
}
