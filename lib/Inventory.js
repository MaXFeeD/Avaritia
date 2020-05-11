LIBRARY({
	name: "Inventory",
	version: 1,
	shared: false,
	api: "CoreEngine"
});

var Inventory = {
	getSlot: function(slot) {
		return Player.getInventorySlot(slot);
	},

	getArmorSlot: function(slot) {
		return Player.getArmorSlot(slot);
	},
	haveItem: function(id, data) {
		if (!data) data = 0;
		for (var i = 0; i < 36; i++) {
			var slot = Player.getInventorySlot(i);
			if ((id == slot.id && data == slot.data) || id == slot.id)
				return true;
		}
		return false;
	},
	getItemSlot: function(id, data){
		if(this.haveItem(id, data))
		for (var i = 0; i < 36; i++) {
			var slot = Player.getInventorySlot(i);
			if (id == slot.id && (data == -1 || data == slot.data)) {
				return slot;
			}
		}
		else return;
	},
	damageItem: function(damage) {
		var item = Player.getCarriedItem();
		item.data += damage;
		if (item.data >= Item.getMaxDamage(item.id)) {
			item.id = 0;
			item.count = 0;
			item.data = 0;
		}
		// }else Game.tipMessage("Durability: "+(Item.getMaxDamage(item.id)-item.data)+"/"+Item.getMaxDamage(item.id));
		Player.setCarriedItem(item.id, 1, item.data, item.extra);
	},
	retrieveItem: function(id, data) {
		if (!data) data = -1;
		for (var i = 0; i < 36; i++) {
			var slot = Player.getInventorySlot(i);
			if (id == slot.id && (data == -1 || data == slot.data)) {
				slot.count--;
				if (slot.count > 0) {
					Player.setInventorySlot(i, slot.id, slot.count, slot.data);
				} else {
					Player.setInventorySlot(i, 0, 0, 0);
				}
				return true;
			}
		}
		return false;
	}
}

EXPORT("Inventory", Inventory);
