IDRegistry.genItemID("gypMatter");
Item.createItem("gypMatter", "Ghyper Matter", {
	name: "matter_cluster", meta: 1
});

Item.registerIconOverrideFunction(ItemID.gypMatter, function(item, texture){
return {name: "matter_cluster", meta: 0};
});

function getItemInPlayerInventory(id, count, data){
  var total = 0;
  for(var i = 9; i < 45; i++){
    var slot = Player.getInventorySlot(i);
    if(slot.id == id && (slot.data == data || data == -1)) total += slot.count;
  }
  return total >= count;
}

function ClusterStart(xpos,ypos,zpos){
	var drop = [];
	var blockID = World.getBlockID(xpos, ypos, zpos);
	blockID;
	drop.push(blockID);
	if(!getItemInPlayerInventory(ItemID.gypMatter, 1, 0)){
		Player.addItemToInventory(ItemID.gypMatter, 1);
	}
	Item.registerUseFunction("gypMatter", function(coords, item, block){
		for(var i in drop){
			World.drop(coords.x, coords.y, coords.z, drop[i]);
			//drop.pop();
		}	
	});
	Callback.addCallback("tick", function (){
		if(drop[4095]!=null){
			setRequiresIconOverride(ItemID.gypMatter, true);
		}
	});
}





