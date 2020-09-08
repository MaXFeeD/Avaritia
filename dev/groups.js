const AVARITIA_TABLE = "avaritia_table";
const AVARITIA_TABLE_NAME = Translation.translate("Workbenches");
const AVARITIA_RESOURCE_BLOCKS = "avaritia_resource_blocks";
const AVARITIA_RESOURCE_BLOCKS_NAME = Translation.translate("Resource Blocks");
const AVARITIA_MACHINES = "avaritia_machines";
const AVARITIA_MACHINES_NAME = Translation.translate("Machines");

const AVARITIA_RESOURCE_ITEMS = "avaritia_res_it";
const AVARITIA_RESOURCE_ITEMS_NAME = Translation.translate("Resources");
const AVARITIA_SINGULARITIES = "avaritia_singularity";
const AVARITIA_SINGULARITIES_NAME = Translation.translate("Singularities");
const AVARITIA_FOOD = "avariria_food";
const AVARITIA_FOOD_NAME = Translation.translate("Food");
const AVARITIA_TOOLS = "avaritia_tools";
const AVARITIA_TOOLS_NAME = Translation.translate("Tools");
const AVARITIA_ARMOR = "avaritia_armor";
const AVARITIA_ARMOR_NAME = Translation.translate("Armor");

Item.addCreativeGroup(AVARITIA_TABLE, AVARITIA_TABLE_NAME, [
	BlockID.compreBlock,
	BlockID.dcompreBlock,
	BlockID.extWorckbench
]);

Item.addCreativeGroup(AVARITIA_RESOURCE_BLOCKS, AVARITIA_RESOURCE_BLOCKS_NAME, [
	BlockID.neutroniumBlock,
	BlockID.infBlock,
	BlockID.crystal_matrixAV
]);

Item.addCreativeGroup(AVARITIA_MACHINES, AVARITIA_MACHINES_NAME, [
	BlockID.neutCo,
	BlockID.compressorAv
]);

Item.addCreativeGroup(AVARITIA_RESOURCE_ITEMS, AVARITIA_RESOURCE_ITEMS_NAME, [
	ItemID.crystal_matrix_ingot,
	ItemID.diamond_lattice,
	ItemID.neutron_pile,
	ItemID.neutron_nugget,
	ItemID.ingotNeutronium,
	ItemID.catalystInfinity,
	ItemID.ingotInfinity,
	ItemID.record_fragment
]);

Item.addCreativeGroup(AVARITIA_SINGULARITIES, AVARITIA_SINGULARITIES_NAME, [
	ItemID.ironsing,
	ItemID.goldsing,
	ItemID.lapissing,
	ItemID.redstonesing,
	ItemID.quartzsing
]);

Item.addCreativeGroup(AVARITIA_FOOD, AVARITIA_FOOD_NAME, [
	ItemID.ultimstew,
	ItemID.cosmMeatballs
]);

Item.addCreativeGroup(AVARITIA_TOOLS, AVARITIA_TOOLS_NAME, [
	ItemID.cosmSword,
	ItemID.skull_sword,
	ItemID.infbow,
	ItemID.cosmPickaxe,
	ItemID.cosmhammer,
	ItemID.cosmShovel,
	ItemID.cosmdes,
	ItemID.cosmAxe,
	ItemID.cosmHoe
]);

Item.addCreativeGroup(AVARITIA_ARMOR, AVARITIA_ARMOR_NAME, [
	ItemID.inf_helmet,
	ItemID.inf_chestplate,
	ItemID.inf_leggings,
	ItemID.inf_boots
]);
