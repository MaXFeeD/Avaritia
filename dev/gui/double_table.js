var guiDouble = new UI.StandartWindow({
	standart: {
		header: {
			text: {
				text: "Double compressed crafting table"
			}
		},
		background: {
			color: android.graphics.Color.parseColor("#c6c6c6")
		},
		inventory: {
			standart: true
		}
	},
	drawing: [],
	elements: {}
});

TileEntity.registerPrototype(BlockID.dcompreBlock, {
	getGuiScreen: function() {
		return guiDouble;
	}
});
