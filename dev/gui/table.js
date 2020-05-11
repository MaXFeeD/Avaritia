var guiCompressedTable = new UI.StandartWindow({
	standart: {
		header: {
			text: {
				text: "Compressed crafting table"
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

TileEntity.registerPrototype(BlockID.compreBlock, {
	getGuiScreen: function() {
		return guiCompressedTable;
	}
});
