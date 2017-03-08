package com.elvarg.world.content.stairs;

import java.util.ArrayList;

import com.elvarg.GameConstants;
import com.elvarg.util.JsonLoader;
import com.elvarg.world.entity.impl.player.Player;
import com.elvarg.world.model.Animation;
import com.elvarg.world.model.Position;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public final class Stairs {
	private static ArrayList<Stair> stairs = new ArrayList<Stair>();

	public static JsonLoader parseStairs() {
		return new JsonLoader() {
			@Override
			public void load(JsonObject reader, Gson builder) {
				int id = 0;
				int anim = -1;
				int face = -1;
				Position pos = null;
				Position playerPos = null;
				if (reader.has("id"))
					id = reader.get("id").getAsInt();
				if (reader.has("animation"))
					anim = reader.get("animation").getAsInt();
				if (reader.has("playerface"))
					face = reader.get("playerface").getAsInt();
				if (reader.has("stairpos")) {
					JsonObject stairPosition = reader.get("stairpos").getAsJsonObject();
					pos = new Position(stairPosition.get("x").getAsInt(), stairPosition.get("y").getAsInt(),
							stairPosition.get("z").getAsInt());
				}
				if (reader.has("playerpos")) {
					JsonObject playerpos = reader.get("playerpos").getAsJsonObject();
					playerPos = new Position(playerpos.get("x").getAsInt(), playerpos.get("y").getAsInt(),
							playerpos.get("z").getAsInt());
				}
				if (face != -1 && anim == -1)
					stairs.add(new Stair(id, pos, playerPos).setPlayerFace(face));
				else if (face != -1 && anim != -1)
					stairs.add(new Stair(id, pos, playerPos).setPlayerFace(face).setAnimation(anim));
				else if (face == -1 && anim != -1)
					stairs.add(new Stair(id, pos, playerPos).setAnimation(anim));
				else
					stairs.add(new Stair(id, pos, playerPos));
			}

			@Override
			public String filePath() {
				return GameConstants.DEFINITIONS_DIRECTORY + "stairs_ladders.json";
			}
		};
	}

	public static void handleStairs(final Player player, int id, Position pos) {
		for (Stair stair : stairs) {
			if (stair.getId() == id && stair.getPosition().equals(pos)) {
				if (stair.getAnimation() != -1)
					player.performAnimation(new Animation(stair.getAnimation()));
				player.moveTo(stair.getPlayerTelePos());
				if (stair.getPlayerFace() != -1) {
					int[] directionDeltas = getDirectionDelta(stair.getPlayerFace());
					player.setPositionToFace(player.getPosition().copy().add(directionDeltas[0], directionDeltas[1]));
				}
			}
		}
	}

	public static boolean containsID(int id) {
		for (Stair stair : stairs)
			if (stair.getId() == id)
				return true;
		return false;
	}
	private static int[] getDirectionDelta(int i) {
		switch (i) {
		case 1:
			return new int[] { 0, 1 };
		case 2:
			return new int[] { 1, 1 }; 
		case 4:
			return new int[] { 1, 0 };
		case 7:
			return new int[] { 1, -1 };
		case 6:
			return new int[] { 0, -1 };
		case 5:
			return new int[] { -1, -1 }; 
		case 3:
			return new int[] { -1, 0 };
		case 0:
			return new int[] { -1, 1 }; 
		default:
			return new int[] { 0, 0 };
		}
	}
}
