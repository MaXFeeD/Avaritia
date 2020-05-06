IMPORT("RecipeTileEntityLib");

		           //table1

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

               //table2

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

         //general table

/*let slot = this.container.getSlot("slot");
if(slot.id == 266) return true*/
var extWorckbench = new UI.StandartWindow({
	standart: {header: {text: {text: "Extreme crafting table"}},
	background: {color: android.graphics.Color.parseColor("#c6c6c6")},
	inventory: {standart: true}},
	drawing: [],
	elements: {
		"scale_0": {type: "scale", x: 843, y: 252, direction: 0, bitmap: "arrow", scale: 3.2, value: 1},
		/*"guid": {type: "button", x: 920, y: 50, scale: 1.9, bitmap: "guid", bitmap2: "guid_press",
		clicker: {onClick: function(){
		container.openAs(guid1);
		}}
	    }, */
		"outputSlot": {type: "slot", x: 930, y: 250, size: 50},
		}
		});
		
let gc = extWorckbench.getContent();
let ryad = 0;
let xx = 380;
let yy = 50;
for (let i = 0; i < 81; i++) 
{
    gc.elements["inputSlot" + i] = 
	{
        type: "slot",
        x: xx,
        y: yy,
		size: 50
    };
    xx += 50;
    ryad++;
    if (ryad == 9) 
	{
        xx = 380;
        yy += 50;
        ryad = 0;
    }
}
		
RecipeTE.registerWorkbench("extWorckbench",
{
    rows:9,
    cols:9,
    GuiScreen:extWorckbench,
});

              //guid

var guid1 = new UI.StandartWindow({
	standart: {
	background: {color: android.graphics.Color.parseColor("#c6c6c6")}, },
	drawing: [],
	elements: {
		"image_0": {type: "image", x: 142, y: 10, bitmap: "extreme_nei", scale: 2.85},
		//"slot_0": {type: "slot", x: 173, y: 73, size: 52, visual: false, bitmap: "custom.slot_default", needClean: false, isTransparentBackground: false},
		"left": {type: "button", x: 20, y: 210, scale: 3.15, bitmap: "guid_left", bitmap2: "guid_left_press", 
		   clicker: {onClick: function(){
		      container.openAs(extWorckbench);
			  container.close(guid1);
		}}
		},
		"right": {type: "button", x: 880, y: 210, scale: 3.15, bitmap: "guid_right", bitmap2: "guid_right_press"},
		"result": {type: "slot", x: 726, y: 266, size: 74, visual: true},
		"name": {type: "text", x: 310, y: 30, width: 120, height: 16, text: "This is a Text element"},
	}
});
let g1 = guid1.getContent();
let r1 = 0;
let xg1 = 173;
let yg1 = 73;
for (let i = 0; i < 81; i++) 
{
    g1.elements["slot" + i] = 
	{
        type: "slot",
        x: xg1,
        y: yg1,
		size: 52,
		visual: true,
    };
    xg1 += 52;
    r1++;
    if (r1 == 9) 
	{
        xg1 = 173;
        yg1 += 52;
        r1 = 0;
    }
}


             //collector

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

const COLLECTOR_MAX = 6000;

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
		let inputC = [];
	   let outputC = [];
	 	  for(i=0;i<1;i++){
	     inputC.push("slot"+i); 
	     outputC.push("slot"+i);
		 }
  return {input: inputC, output: outputC}
	 	},
});

            //compressor
			
var compressorGUI = new UI.StandartWindow({
	standart: {header: {text: {text: "Compressor"}},
	background: {color: android.graphics.Color.parseColor("#c6c6c6")}, inventory: {standart: true}},
	drawing: [{type:"bitmap",x:574,y:238,scale:3.6,bitmap:"progress_background"},],
	elements: {
		"slot_0": {type: "slot", x: 500, y: 230, size: 68, visual: false, needClean: false, isTransparentBackground: false},
		"progress": {type: "scale", x: 574, y: 238, direction: 0, bitmap: "progress", scale: 3.6, value: 1},
		"sing": {type: "scale", x: 657, y: 228, direction: 0, bitmap: "sing", scale: 2.3, value: 1},
		"slot_1": {type: "slot", x: 740, y: 230, size: 68, visual: false, needClean: false, isTransparentBackground: false},
		"count": {type: "text", x: 580, y: 310, width: 120, height: 16, text: "0/0"},
		"block": {type: "text", x: 580, y: 360, width: 120, height: 16, text: ""},
	}
});

		
var ModRecipe = {
	recipe:{},
	addRecipe:function(a,b){
		this.recipe[a] = b;
		},
		getRecipe: function (a){
			if(this.recipe[a]){
				return this.recipe[a];
				}
		},
	};

ModRecipe.addRecipe(42,{
	count:850,
	out:ItemID.ironsing,
	name: "Iron",
	});

ModRecipe.addRecipe(41,{
	count:650,
	out:ItemID.goldsing,
	name: "Gold",
	});

ModRecipe.addRecipe(155,{
	count:750,
	out:ItemID.quartzsing,
	name: "Quartz",
	});

ModRecipe.addRecipe(152,{
	count:950,
	out:ItemID.redstonesing,
	name: "Redstone",
	});

ModRecipe.addRecipe(22,{
	count:850,
	out:ItemID.lapissing,
	name: "Lapis",
	});




TileEntity.registerPrototype(BlockID.compressorAv,{
	defaultValues:{
		count:0,
		progress:0,
		end:0,
		id:0,
		block: "",
		},
		tick:function(){
			var slot0 = this.container.getSlot("slot_0");
			var slot1 = this.container.getSlot("slot_1");
			var recipe = ModRecipe.getRecipe(slot0.id);
			if(recipe){
			if(this.data.id==0&&slot0.id!=0){
				this.data.id = slot0.id;
				}
			if(slot0.id==this.data.id&&slot0.id!=0){
				this.data.block = recipe.name;
				this.data.count += 1;
				this.data.end = recipe.count; 
				this.data.progress += 1/this.data.end;
				slot0.count--;
				}
				if(this.data.progress >= 1){
					slot1.id = recipe.out;
					slot1.count++;
					this.data.count -= this.data.end;
					this.data.id = 0;
					this.data.progress = 0;
					}
				}
				this.container.setText("block", "Block of " + this.data.block);
				this.container.setText("count",this.data.count +" / "+this.data.end);
				this.container.setScale("progress",this.data.progress);
				this.container.validateAll();
			},
	getGuiScreen:function(){
		return compressorGUI;
		}
	});
  
 
