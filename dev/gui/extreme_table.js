let ssize = UI.getScreenHeight()/12;
let getW = UI.getScreenHeight()*1.7;
let guiExtremeTable = new UI.StandartWindow({
	standart: {
		header: {
			text: {
				text: Translation.translate('Extreme Crafting Table')
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
	elements: {
		scale_0: {
			type: "scale",
			x: getW/2.2 + ssize*9, 
			y: ssize*5,
			direction: 0,
			bitmap: "progress_background",
			scale: ssize/20,
			value: 1
		},
		outputSlot: {
			type: "slot",
			x: getW/2.4 + ssize*11, 
			y: ssize*4.9,
			size: ssize
		}
	}
});


let content = guiExtremeTable.getContent();
let row = 0, x = getW/2.2, y = UI.getScreenHeight()/13.7;
for (let i = 0; i < 81; i++) {
	content.elements["inputSlot" + i] = {
		type: "slot",
		x: x, y: y,
		size: ssize
	};
	x += ssize;
	row++;
	if (row >= 9) {
		x = getW/2.2;
		y += ssize;
		row = 0;
	}
}

/*
RecipeTE.registerWorkbench('extWorckbench', {
	window: guiExtremeTable,
	columns: 9,
	rows: 9
});

RecipeTE.registerTileEntity(BlockID['extWorckbench'], new RecipeTE.WorkbenchTileEntity('extWorckbench'));
*/

const ExtremeTable = new RecipeTE.Workbench({
    columns: 9,
    rows: 9
});
 

let __extends = (this && this.__extends) || (function () {
    let extendStatics = function (d, b) {
        extendStatics = Object.setPrototypeOf ||
            ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
            function (d, b) { for (let p in b) if (Object.prototype.hasOwnProperty.call(b, p)) d[p] = b[p]; };
        return extendStatics(d, b);
    };
    return function (d, b) {
        if (typeof b !== "function" && b !== null)
            throw new TypeError("Class extends value " + String(b) + " is not a constructor or null");
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();


let ExtremeTableTE = /** @class */ (function (_super) {
    __extends(ExtremeTableTE, _super);
    function ExtremeTableTE() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    ExtremeTableTE.prototype.getScreenName = function () { return "main"; };
    ExtremeTableTE.prototype.getScreenByName = function () { return guiExtremeTable; };
    ExtremeTableTE.prototype.getInputSlots = function () { return "inputSlot"; };
    ExtremeTableTE.prototype.getOutputSlot = function () { return "outputSlot"; };
    return ExtremeTableTE;
}(RecipeTE.WorkbenchTileEntity));


TileEntity.registerPrototype(BlockID["extWorckbench"], new ExtremeTableTE(ExtremeTable));
