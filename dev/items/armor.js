IDRegistry.genItemID("inf_helmet");
Item.createArmorItem("inf_helmet", "Infinity Helmet", {
	name: "helmetAV",
	meta: 0
}, {
	isTech: false,
	armor: 3,
	type: "helmet",
	texture: "armor/infinity_armor_full_1.png",
	durability: Number.MAX_VALUE
});
ArmorTick.attachTo({
	id: ItemID.inf_helmet,
	type: 0,
	tick: function() {
<<<<<<< HEAD
		Entity.addEffect(Player.get(), 16, 1, 1, true, true);
		Entity.addEffect(Player.get(), 23, 190, 1, true, true);
		Entity.addEffect(Player.get(), 13, 190, 1, true, true);
=======
		Entity.addEffect(Player.get(), 16, 1, 260, false, false);
		Entity.addEffect(Player.get(), 23, 190, 260, false, false);
		Entity.addEffect(Player.get(), 13, 190, 260, false, false);
>>>>>>> target/master
	}
});

IDRegistry.genItemID("inf_chestplate");
Item.createArmorItem("inf_chestplate", "Infinity Chestplate", {
	name: "chestaplateAV",
	meta: 0
}, {
	isTech: false,
	armor: 8,
	type: "chestplate",
	texture: "armor/infinity_armor_full_1.png",
	durability: Number.MAX_VALUE
});


/*
Callback.addCallback('LevelLoaded', function(){
	var IDplayers =  Network.getConnectedPlayers();
	const attach = new AttachableRender(IDplayers[0]);
	arender.addPart('body').endPart().addPart('lwing', 'body', lwing).setOffset(-1, -10, 3).setTexture('armor/infinity_armor_wing_gen.png').endPart().addPart('rwing', 'body', rwing).setOffset(1, -10, 3).setTexture('armor/infinity_armor_wing_gen.png').endPart().addPart('lglow', 'body', lglow).setOffset(-1, -10, 3).setTexture('armor/infinity_armor_wingglow.png').endPart().addPart('rglow', 'body', rglow).setOffset(1, -10, 3).setTexture('armor/infinity_armor_wingglow.png').endPart();
			*/
ArmorTick.attachTo({
	id: ItemID.inf_chestplate,
	type: 1,
	tick: function() {
<<<<<<< HEAD
		Entity.addEffect(Player.get(), 11, 190, 1, true, true);
		Entity.addEffect(Player.get(), 10, 190, 1, true, true);
		if(Game.getGameMode() != 1) {
			Player.setFlyingEnabled(true);
		}
	}
=======
		if(Player.getFlying()){
			alert('fly');
            }
			//if(time == 0){
			//if(attach.isAttached)	arender.clear();
					/*attach.setRenderer(arender);
				}else {
					lglow.clear();
					rglow.clear();
				//if(attach.isAttached)	arender.clear();
					//attach.setRenderer(arender);
					//arender.addPart('body').endPart().addPart('lwing', 'body', lwing).setOffset(-1, -10, 3).setTexture('armor/infinity_armor_wing_gen.png').endPart().addPart('rwing', 'body', rwing).setOffset(1, -10, 3).setTexture('armor/infinity_armor_wing_gen.png').endPart();
					}}
					else if(attach.isAttached){
						alert('lol');
						lwing.clear();
						rwing.clear();
						//attach.destroy();
						}*/
		Entity.addEffect(Player.get(), 11, 190, 260, false, false);
		Entity.addEffect(Player.get(), 10, 190, 260, false, false);
		if(Game.getGameMode() != 1) Player.setFlyingEnabled(true);
	    
    }
>>>>>>> target/master
});

Callback.addCallback("tick", function() {
	if (Player.getArmorSlot(1).id ==! ItemID.inf_chestplate && Game.getGameMode() != 1) {
		Player.setFlyingEnabled(false);
	}
});

IDRegistry.genItemID("inf_leggings");
Item.createArmorItem("inf_leggings", "Infinity Leggings", {
	name: "legginsAV",
	meta: 0
}, {
	isTech: false,
	armor: 6,
	type: "leggings",
	texture: "armor/infinity_armor_full_2.png",
	durability: Number.MAX_VALUE
});
ArmorTick.attachTo({
	id: ItemID.inf_leggings,
	type: 2,
	tick: function() {
<<<<<<< HEAD
		Entity.addEffect(Player.get(), 1, 9, 1, true, true);
=======
		Entity.addEffect(Player.get(), 1, 9, 260, false, false);
>>>>>>> target/master
	}
});

IDRegistry.genItemID("inf_boots");
Item.createArmorItem("inf_boots", "Infinity Boots", {
	name: "bootAV",
	meta: 0
}, {
	isTech: false,
	armor: 3,
	type: "boots",
	texture: "armor/infinity_armor_full_1.png",
	durability: Number.MAX_VALUE
});


Callback.addCallback("EntityHurt", function(victim, attacker){
	if(Player.getArmorSlot(0).id == ItemID.inf_helmet &&
	Player.getArmorSlot(1).id == ItemID.inf_chestplate &&
	Player.getArmorSlot(2).id == ItemID.inf_leggings &&
	Player.getArmorSlot(3).id == ItemID.inf_boots && 
	attacker == Player.get())Game.prevent();
});

ArmorTick.attachTo({
	id: ItemID.inf_boots,
	type: 3,
	tick: function() {
<<<<<<< HEAD
		Entity.addEffect(Player.get(), 8, 3, 1, true, true);
=======
		
		Entity.addEffect(Player.get(), 8, 3, 260, false, false);
>>>>>>> target/master
	}
});
