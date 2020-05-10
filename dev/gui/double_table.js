var GuiDouble = new UI.StandartWindow({
	standart: {header: {text: {text: Translation.translate("Double compressed crafting table")}},
	background: {color: android.graphics.Color.parseColor("#c6c6c6")}, 
	inventory: {standart: true}},
	drawing: [],
	elements: {
		"text_0": {type: "text", x: 360, y: 260, width: 120, height: 16, text: "Я же сказал бля, этот верстак тож не роботает!!!"},
	}
});		
TileEntity.registerPrototype(BlockID.dcompreBlock, {
  defaultValues: {},
  getGuiScreen: function(){
    return GuiDouble;
  },
  tick: function(){}
});
