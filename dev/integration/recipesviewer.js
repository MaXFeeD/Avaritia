let RV;

ModAPI.addAPICallback("RecipeViewer", function(api){
    RV = api.Core;
			


RV.registerRecipeType("neutron_compressor", {
	title: Translation.translate("Compressor Neutronium"),
	contents: {
		icon: BlockID.compressorAv,
		drawing: [],
		elements: {
				intext: {type: "text",x: 432,y: 157,width: 68,height: 16,font: {alignment: 1}},
				input0: {type: "slot",x: 402,y: 192,size: 68,visual: true,needClean: true,isTransparentBackground: true,bitmap: "transparent"},
				input1: {type: "slot",x: 490,y: 192,size: 68,visual: false,needClean: false,isTransparentBackground: false},
				progress: {type: "scale",x: 574,y: 200,pixelate: true,bitmap: "progress_singular",background: "progress_background",scale: 3.6,value: 0},
				singular: {type: "scale",x: 667,y: 190,direction: 1,pixelate: true,bitmap: "singular",background: "singular_background",scale: 4.6,value: 1},
				output0: {type: "slot",x: 760,y: 192,size: 68,visual: false,needClean: false,isTransparentBackground: false},
				count: {type: "text",x: 660,y: 272,width: 120,height: 16,font: {alignment: 1}},
				outext: {type: "text",x: 883,y: 157,width: 68,height: 16,font: {alignment: 1}},
				output1: {type: "slot",x: 858,y: 192,size: 68,visual: true,needClean: true,isTransparentBackground: true,bitmap: "transparent"}
			}
		},
		getList: function(id, data, isUsage){
			let result;
			if(isUsage){
				result = Compressor.getRecipe(id);
				return result ? [{
					input: [
						/*{id: 42, count: 400, data: 0},
						{id: 41, count: 200, data: 0},
						{id: 155, count: 200, data: 0}*/
						{id: id || 0, count: 1, data: data}
					],
					output: [
						{id: result[0] || 0, count: result[1] || 0, data: 0},
						{id: result[2] || 0, count: result[3] || 0, data: 0},
						{id: result[4] || 0, count: result[5] || 0, data: 0},
						{id: result[6] || 0, count: result[7] || 0, data: 0},
						{id: result[8] || 0, count: result[9] || 0, data: 0},
						{id: result[10] || 0, count: result[11] || 0, data: 0},
						{id: result[12] || 0, count: result[13] || 0, data: 0},
						//{id: ItemID.ironsing, count: 1, data: 0}
						]
					}] : [];
				}
				let list = [];
				let recipe = Compressor.recipes;
				for(let key in recipe){
					result = recipe[key];
					if(result[0] == id || result[2] == id || result[4] == id || result[6] == id || result[8] == id || result[10] == id || result[12] == id){
						list.push({
						input: [{id: parseInt(key), count: 1, data: 0}],
						output: [
							{id: result[0] || 0, count: result[1] || 0, data: 0},
							{id: result[2] || 0, count: result[3] || 0, data: 0},
							{id: result[4] || 0, count: result[5] || 0, data: 0},
							{id: result[6] || 0, count: result[7] || 0, data: 0},
							{id: result[8] || 0, count: result[9] || 0, data: 0},
							{id: result[10] || 0, count: result[11] || 0, data: 0},
							{id: result[12] || 0, count: result[13] || 0, data: 0},
							]
						});
					}
				}
				return list;
				},
				getAllList: function(){
					let list = [];
					let recipe = Compressor.recipes;
					let result;
					for(let key in recipe){
						result = recipe[key];
						list.push({
						input: [{id: key, count: 1, data: 0}],
						output: [
							{id: result[0] || 0, count: result[1] || 0, data: 0},
							{id: result[2] || 0, count: result[3] || 0, data: 0},
							{id: result[4] || 0, count: result[5] || 0, data: 0},
							{id: result[6] || 0, count: result[7] || 0, data: 0},
							{id: result[8] || 0, count: result[9] || 0, data: 0},
							{id: result[10] || 0, count: result[11] || 0, data: 0},
							{id: result[12] || 0, count: result[13] || 0, data: 0},
							]
						});
					}
					return list;
				}
		});
	
});
