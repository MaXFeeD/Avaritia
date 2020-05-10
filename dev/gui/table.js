var container = new UI.Container();  
var GuiComp = new UI.StandartWindow({
	standart: {header: {text: {text: Translation.translate("Compressed crafting table")}},
	background: {color: android.graphics.Color.parseColor("#c6c6c6")}, 
	inventory: {standart: true}},
	drawing: [],
	elements: {
		"text_0": {type: "text", x: 338, y: 260, width: 120, height: 16, text: "Хули ты сюда смотришь?!! Этот верстак не робочий!!!"},
	}
});		
TileEntity.registerPrototype(BlockID.compreBlock, {
  defaultValues: {},
  getGuiScreen: function(){
    return GuiComp;
  },
  tick: function(){}
});
