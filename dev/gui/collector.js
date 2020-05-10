var guiCollector = new UI.StandartWindow({
	standart: {header: {text: {text: "Collector"}},
	background: {color: android.graphics.Color.parseColor("#c6c6c6")},
	inventory: {standart: true}},
	drawing: [],
	elements: {
		"slot_0": {type: "slot", x: 570, y: 160, size: 102}, 
		"progress": {type: "text", x: 545, y: 290, width: 120, height: 16, text: "Progress: 0%"},
	}
});

var COLLECTOR_MAX = 6000;

TileEntity.registerPrototype(BlockID.neutCo, {
defaultValues: {
 jin:0,
},
getGuiScreen: function(){
 return guiCollector;
},
tick: function() {
 slot = this.container.getSlot("slot_0");
 this.container.setText("progress", "Progress: " + parseInt(this.data.jin / COLLECTOR_MAX * 100) + "%");
 if (++this.data.jin >= COLLECTOR_MAX && slot.count < 64){
  if (slot.id == ItemID.neutron_pile) {
   slot.count ++;
   this.data.jin = 0;
  } else if (slot.id == 0 && !slot.count) {
   slot.id = ItemID.neutron_pile;
  } else this.data.jin = COLLECTOR_MAX;
 }
},
   getTransportSlots: function(){
		var inputC = [];
	   var outputC = [];
	 	  for(i=0;i<1;i++){
	     inputC.push("slot"+i); 
	     outputC.push("slot"+i);
		 }
  return {input: inputC, output: outputC};
	 	},
});
