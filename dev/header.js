/*
 ▄▄▄          ██▒   █▓    ▄▄▄          ██▀███      ██▓   ▄▄▄█████▓    ██▓    ▄▄▄      
▒████▄       ▓██░   █▒   ▒████▄       ▓██ ▒ ██▒   ▓██▒   ▓  ██▒ ▓▒   ▓██▒   ▒████▄    
▒██  ▀█▄      ▓██  █▒░   ▒██  ▀█▄     ▓██ ░▄█ ▒   ▒██▒   ▒ ▓██░ ▒░   ▒██▒   ▒██  ▀█▄  
░██▄▄▄▄██      ▒██ █░░   ░██▄▄▄▄██    ▒██▀▀█▄     ░██░   ░ ▓██▓ ░    ░██░   ░██▄▄▄▄██ 
 ▓█   ▓██▒      ▒▀█░      ▓█   ▓██▒   ░██▓ ▒██▒   ░██░     ▒██▒ ░    ░██░    ▓█   ▓██▒
 ▒▒   ▓▒█░      ░ ▐░      ▒▒   ▓▒█░   ░ ▒▓ ░▒▓░   ░▓       ▒ ░░      ░▓      ▒▒   ▓▒█░
  ▒   ▒▒ ░      ░ ░░       ▒   ▒▒ ░     ░▒ ░ ▒░    ▒ ░       ░        ▒ ░     ▒   ▒▒ ░
  ░   ▒           ░░       ░   ▒        ░░   ░     ▒ ░     ░          ▒ ░     ░   ▒   
      ░  ░         ░           ░  ░      ░         ░                  ░           ░  ░
                  ░                                                                   
   
   Copyright 2018-2020 StoneBlock Team (vk.com/club178626915)
   Developed with love by Foldik and MaXFeeD
   
*/

IMPORT("RecipeTileEntity");
IMPORT("ToolType");
IMPORT("Bow");
IMPORT("ItemAnimator:1");
IMPORT("SoundAPI");
IMPORT("Timer");

alert('               <<<<<<<<AVARITIA>>>>>>>>');
for(let i = 1; i < 6; i++)alert('WARNING!!! Longbow of the Heavens is UNstable!!!');

const AvaritiaAPI = {
};

ModAPI.registerAPI("AvaritiaAPI", {
	Core: AvaritiaAPI
});

Item.setRequiresIconOverride = ModAPI.requireGlobal("Item.setRequiresIconOverride");
Game.getGameMode = ModAPI.requireGlobal("Level.getGameMode");
var EntityDataRegistry = ModAPI.requireGlobal("EntityDataRegistry");

var ArmorTick = {
	attachTo: function(arg) {
		if (!arg.tick || arg.id == undefined || arg.type == undefined) return;
		Callback.addCallback("tick", function() {
			if (Player.getArmorSlot(arg.type).id == arg.id) {
				if (arg.tick instanceof Function) {
					arg.tick();
				}
			}
		});
	}
};

function makeReplaceable(item, id, replacement) {
	if (item == undefined || typeof item != "object" || typeof item.id != "number") return;
	if (item.id == id) Player.setCarriedItem(replacement, item.count, item.data, item.extra);
	else if (item.id == replacement) Player.setCarriedItem(id, item.count, item.data, item.extra);
}

function addNamedCallback(action, current) {
	Callback.addCallback(current, function() {
		var arguments = Array.prototype.slice.call(arguments);
		(arguments.unshift(current), action.apply(Callback, arguments));
	});
}

function makeSimplifiedCallback(action) {
	var arguments = Array.prototype.slice.call(arguments);
	if (arguments.length == 0) return Logger.Log("can't create makeSimplifiedCallback without action & names", "ERROR");
	if (arguments.length == 1) return Logger.Log("no names specified for makeSimplifiedCallback", "ERROR");
	arguments.shift(); // removing action from callback names, it attached into function
	arguments.forEach(function(current) { addNamedCallback(action, current); });
}

function playerHasItem(id, count, data) {
	var total = 0;
	for (var i = 9; i < 45; i++) {
		var slot = Player.getInventorySlot(i);
		if (slot.id == id && (slot.data == data || data == -1)) {
			total += slot.count;
		}
	}
	return total >= count;
}
