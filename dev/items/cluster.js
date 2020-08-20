IDRegistry.genItemID("gypMatter");
Item.createItem("gypMatter", "Ghyper Matter", {
	name: "matter_cluster",
	meta: 1
}, {
	stack: 1
});

Item.registerIconOverrideFunction(ItemID.gypMatter, function(item, texture) {
	return {
		name: "matter_cluster",
		meta: 0
	};
});

function getItemInPlayerInventory(id, count, data){
	var total = 0;
	for (var i = 9; i < 45; i++) {
		var slot = Player.getInventorySlot(i);
		if (slot.id == id && (slot.data == data || data == -1)) {
			total += slot.count;
		}
	}
	return total >= count;
}

var drop = new Array();
function checkClusterable(xpos, ypos, zpos) {
	let ptime = World.getThreadTime() % 2;
	let blockID = World.getBlock(xpos, ypos, zpos);
	drop.push(blockID);
	if (!getItemInPlayerInventory(ItemID.gypMatter, 1, 0)) {
		Player.addItemToInventory(ItemID.gypMatter, 1, 0);
	}else if(drop[4095] != null){
		if(ptime == 0 || ptime == 2)alert(Translation.translate('Free the cluster of matter'));
	}
	
	Callback.addCallback("tick", function (){
		if (drop[4095] != null) {
			setRequiresIconOverride(ItemID.gypMatter, true);
		}
	});
}

Item.registerUseFunction("gypMatter", function(coords, item, block) {
		if(Entity.getSneaking(Player.get())){
			for (let i = 0; i < drop.length; i++) {
				World.drop(coords.x, coords.y, coords.z, drop[i][0], 1, drop[i][1]);
				drop.pop();
				i--;
			}
			Player.setCarriedItem(item.id, item.count - 1, 0);
		}
	});
