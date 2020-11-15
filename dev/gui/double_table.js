var guiDouble = new UI.StandartWindow({
	standart: {
		header: {
			text: {
				text: Translation.translate('Double Compressed Crafting Table')
			}
		},
		background: {
			color: android.graphics.Color.parseColor("#c6c6c6")
		},
		inventory: {
			standart: true
		}
	},
	drawing: new Array(),
	elements: new Object()
});

TileEntity.registerPrototype(BlockID.dcompreBlock, {
	getGuiScreen: function() {
		return guiDouble;
	}
});
