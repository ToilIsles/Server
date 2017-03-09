package com.elvarg.world.content.bank;

import com.elvarg.world.entity.impl.npc.NPC;
import com.elvarg.world.entity.impl.player.Player;
import com.elvarg.world.model.dialogue.DialogueManager;
import com.elvarg.world.model.dialogue.DialogueOptions;

public final class BankerNPC {
	private static int [] npcs = new int[]{
			3843
	};
	
	public static boolean isBanker(NPC npc){
		for(int i : npcs)
			if(i == npc.getId())
				return true;
		return false;
	}
	public static void doDialogue(Player player){
		player.setDialogueOptions(new DialogueOptions(){
			@Override
			public void handleOption1(Player player){
				openBank(player);
			}
			@Override
			public void handleOption2(Player player){
				player.getPacketSender().sendInterfaceRemoval();
			}
		});
		DialogueManager.start(player, 11);
	}
	public static void openBank(Player player){
		player.getBank(player.getCurrentBankTab()).open();
	}
}
