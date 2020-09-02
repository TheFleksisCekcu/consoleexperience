package com.fuzs.consoleexperience.config;

import com.fuzs.consoleexperience.client.gui.PositionPreset;
import com.fuzs.consoleexperience.client.tooltip.TextColor;
import com.google.common.collect.Lists;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.List;

@SuppressWarnings("WeakerAccess")
public class ConfigBuildHandler {

	private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

	public static final GeneralConfig GENERAL_CONFIG = new GeneralConfig("general");
	public static final SelectedItemConfig HELD_ITEM_TOOLTIPS_CONFIG = new SelectedItemConfig("helditemtooltips");
	public static final PaperDollConfig PAPER_DOLL_CONFIG = new PaperDollConfig("paperdoll");
	public static final HoveringHotbarConfig HOVERING_HOTBAR_CONFIG = new HoveringHotbarConfig("hoveringhotbar");
	public static final SaveIconConfig SAVE_ICON_CONFIG = new SaveIconConfig("saveicon");
	public static final CoordinateDisplayConfig COORDINATE_DISPLAY_CONFIG = new CoordinateDisplayConfig("coordinates");
	public static final MiscellaneousConfig MISCELLANEOUS_CONFIG = new MiscellaneousConfig("miscellaneous");

	public static class GeneralConfig {

		public final ForgeConfigSpec.BooleanValue heldItemTooltips;
		public final ForgeConfigSpec.BooleanValue paperDoll;
		public final ForgeConfigSpec.BooleanValue hoveringHotbar;
		public final ForgeConfigSpec.BooleanValue saveIcon;
		public final ForgeConfigSpec.BooleanValue coordinateDisplay;

		private GeneralConfig(String name) {

			BUILDER.push(name);

			this.heldItemTooltips = ConfigBuildHandler.BUILDER.comment("Enhances vanilla held item tooltips with information about enchantments, potions effects, shulker box contents and more.").define("Held Item Tooltips", true);
			this.paperDoll = ConfigBuildHandler.BUILDER.comment("Show a small player model in a configurable corner of the screen while the player is performing certain actions like sprinting, sneaking, or flying.").define("Paper Doll", true);
			this.hoveringHotbar = ConfigBuildHandler.BUILDER.comment("Enable the hotbar to hover anywhere on the screen. By default just moves it up a little from the screen bottom.").define("Hovering Hotbar", true);
			this.saveIcon = ConfigBuildHandler.BUILDER.comment("Show an animated icon on the screen whenever the world is being saved (every 45 seconds by default). This only works in singleplayer.").define("Save Icon", true);
			this.coordinateDisplay = ConfigBuildHandler.BUILDER.comment("Always show player coordinates on screen.").define("Coordinate Display", false);

			BUILDER.pop();

		}

	}

	public static class SelectedItemConfig {

		public final AppearanceConfig appearanceConfig;
		public final ForgeConfigSpec.ConfigValue<List<String>> blacklist;
		public final ForgeConfigSpec.IntValue rows;
		public final ForgeConfigSpec.IntValue scale;
		public final ForgeConfigSpec.IntValue displayTime;
		public final ForgeConfigSpec.IntValue xOffset;
		public final ForgeConfigSpec.IntValue yOffset;
		public final ForgeConfigSpec.BooleanValue cacheTooltip;
		public final ForgeConfigSpec.BooleanValue tied;

		private SelectedItemConfig(String name) {

			BUILDER.push(name);

			this.blacklist = ConfigBuildHandler.BUILDER.comment("Disables held item tooltips for specified items and mods, mainly to prevent custom tooltips from overlapping. Enter as either \"modid:item\" or \"modid\" respectively.").define("Blacklist", Lists.newArrayList("examplemod", "examplemod:exampleitem"));
			this.rows = ConfigBuildHandler.BUILDER.comment("Maximum amount of rows to be displayed for held item tooltips.").defineInRange("Rows", 4, 1, 9);
			this.scale = ConfigBuildHandler.BUILDER.comment("Scale of held item tooltips. Works together with \"GUI Scale\" option in \"Video Settings\". A smaller scale might make room for setting more rows.").defineInRange("Scale", 6, 1, 24);
			this.displayTime = ConfigBuildHandler.BUILDER.comment("Amount of ticks the held item tooltip will be displayed for. Set to 0 to always display the tooltip as long as an item is being held.").defineInRange("Display Time", 40, 0, Integer.MAX_VALUE);
			this.xOffset = ConfigBuildHandler.BUILDER.comment("Offset on x-axis from screen center.").defineInRange("X-Offset", 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
			this.yOffset = ConfigBuildHandler.BUILDER.comment("Offset on y-axis from screen bottom.").defineInRange("Y-Offset", 59, 0, Integer.MAX_VALUE);
			this.cacheTooltip = ConfigBuildHandler.BUILDER.comment("Cache tooltip so it doesn't have to be remade every tick. This will prevent it from updating stats like durability while it is displayed. Ineffective when the tooltip is always displayed by setting \"Display Time\" to 0.").define("Cache Tooltip", true);
			this.tied = ConfigBuildHandler.BUILDER.comment("Tie held item tooltips position to the hovering hotbar feature.").define("Tie To Hotbar", true);

			BUILDER.pop();

			this.appearanceConfig = new AppearanceConfig(name + "_appearance");

		}

		public static class AppearanceConfig {

			public final ForgeConfigSpec.BooleanValue moddedTooltips;
			public final ForgeConfigSpec.BooleanValue showDurability;
			public final ForgeConfigSpec.BooleanValue forceDurability;
			public final ForgeConfigSpec.BooleanValue showLastLine;
			public final ForgeConfigSpec.EnumValue<TextColor> textColor;

			private AppearanceConfig(String name) {

				BUILDER.push(name);

				this.moddedTooltips = ConfigBuildHandler.BUILDER.comment("Enables tooltip information added by other mods like Hwyla to be displayed as a held item tooltip.").define("Show Modded Tooltips", false);
				this.showDurability = ConfigBuildHandler.BUILDER.comment("Displays the item's durability as part of its held item tooltip.").define("Show Durability", true);
				this.forceDurability = ConfigBuildHandler.BUILDER.comment("Force the durability to always be on the tooltip. \"Show Durability\" has to be enabled for this to have any effect.").define("Force Durability", true);
				this.showLastLine = ConfigBuildHandler.BUILDER.comment("Show how many more lines there are that currently don't fit the tooltip.").define("Show Last Line", true);
				this.textColor = ConfigBuildHandler.BUILDER.comment("Default text color. Only applied when the text doesn't already have a color assigned internally.").defineEnum("Text Color", TextColor.LIGHT_GRAY);

				BUILDER.pop();

			}

		}

	}

	public static class PaperDollConfig {

		public final DisplayActionsConfig displayActionsConfig;
		public final ForgeConfigSpec.EnumValue<PositionPreset> position;
		public final ForgeConfigSpec.IntValue scale;
		public final ForgeConfigSpec.IntValue xOffset;
		public final ForgeConfigSpec.IntValue yOffset;
		public final ForgeConfigSpec.IntValue displayTime;
        //public final ForgeConfigSpec.EnumValue<HeadMovement> headMovement;
		public final ForgeConfigSpec.BooleanValue potionShift;
		public final ForgeConfigSpec.BooleanValue burning;
		public final ForgeConfigSpec.BooleanValue firstPerson;

		private PaperDollConfig(String name) {

			BUILDER.push(name);

			this.scale = ConfigBuildHandler.BUILDER.comment("Scale of paper doll. Works in tandem with \"GUI Scale\" option in \"Video Settings\".").defineInRange("Scale", 4, 1, 24);
			this.xOffset = ConfigBuildHandler.BUILDER.comment("Offset on x-axis from original doll position.").defineInRange("X-Offset", 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
			this.yOffset = ConfigBuildHandler.BUILDER.comment("Offset on y-axis from original doll position.").defineInRange("Y-Offset", 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
			this.displayTime = ConfigBuildHandler.BUILDER.comment("Amount of ticks the paper doll will be kept on screen after its display conditions are no longer met. Set to 0 to always display the doll.").defineInRange("Display Time", 12, 0, Integer.MAX_VALUE);
			this.position = ConfigBuildHandler.BUILDER.comment("Define a screen corner to display the paper doll in.").defineEnum("Screen Corner", PositionPreset.TOP_LEFT);
            //this.headMovement = ConfigBuildHandler.BUILDER.comment("Set the axis the player head can move on.").defineEnum("Head Movement", HeadMovement.YAW);
			this.potionShift = ConfigBuildHandler.BUILDER.comment("Shift the paper doll downwards when it would otherwise overlap with the potion icons. Only applicable when the \"Screen Corner\" is set to \"TOP_RIGHT\".").define("Potion Shift", true);
			this.burning = ConfigBuildHandler.BUILDER.comment("Disable flame overlay on the hud when on fire and display the burning paper doll instead.").define("Burning Doll", false);
			this.firstPerson = ConfigBuildHandler.BUILDER.comment("Only show the paper doll when in first person mode.").define("First Person Only", true);

			BUILDER.pop();

			this.displayActionsConfig = new DisplayActionsConfig(name + "_displayactions");

		}

		public static class DisplayActionsConfig {

			public final ForgeConfigSpec.BooleanValue sprinting;
			public final ForgeConfigSpec.BooleanValue swimming;
			public final ForgeConfigSpec.BooleanValue crawling;
			public final ForgeConfigSpec.BooleanValue crouching;
			public final ForgeConfigSpec.BooleanValue flying;
			public final ForgeConfigSpec.BooleanValue gliding;
			public final ForgeConfigSpec.BooleanValue riding;
			public final ForgeConfigSpec.BooleanValue spinAttacking;
			public final ForgeConfigSpec.BooleanValue moving;
			public final ForgeConfigSpec.BooleanValue jumping;
			public final ForgeConfigSpec.BooleanValue attacking;
			public final ForgeConfigSpec.BooleanValue using;
			public final ForgeConfigSpec.BooleanValue hurt;

			private DisplayActionsConfig(String name) {

				BUILDER.push(name);

				this.sprinting = ConfigBuildHandler.BUILDER.comment("Display the paper doll while the player is sprinting.").define("Sprinting", true);
				this.swimming = ConfigBuildHandler.BUILDER.comment("Display the paper doll while the player is swimming.").define("Swimming", true);
				this.crawling = ConfigBuildHandler.BUILDER.comment("Display the paper doll when the player is crawling in a tight space.").define("Crawling", true);
				this.crouching = ConfigBuildHandler.BUILDER.comment("Display the paper doll while the player is crouching.").define("Crouching", true);
				this.flying = ConfigBuildHandler.BUILDER.comment("Display the paper doll while the player is using creative mode flight.").define("Flying", true);
				this.gliding = ConfigBuildHandler.BUILDER.comment("Display the paper doll while the player is flying with an elytra.").define("Elytra Flying", true);
				this.riding = ConfigBuildHandler.BUILDER.comment("Show the paper doll when the player is riding any entity.").define("Riding", false);
				this.spinAttacking = ConfigBuildHandler.BUILDER.comment("Show the paper doll when the player is spin attacking using a riptide enchanted trident.").define("Spin Attacking", false);
				this.moving = ConfigBuildHandler.BUILDER.comment("Show the paper doll when the player is moving.").define("Moving", false);
				this.jumping = ConfigBuildHandler.BUILDER.comment("Show the paper doll when the player is jumping.").define("Jumping", false);
				this.attacking = ConfigBuildHandler.BUILDER.comment("Show the paper doll when the player is hitting / breaking / placing any object.").define("Attacking", false);
				this.using = ConfigBuildHandler.BUILDER.comment("Show the paper doll when the player is using an item, like eating.").define("Using", false);
				this.hurt = ConfigBuildHandler.BUILDER.comment("Enable the paper doll if the player is hurt.").define("Hurt", false);

				BUILDER.pop();

			}

		}

	}

	public static class HoveringHotbarConfig {

		public final ForgeConfigSpec.IntValue xOffset;
		public final ForgeConfigSpec.IntValue yOffset;

		private HoveringHotbarConfig(String name) {

			BUILDER.push(name);

			this.xOffset = ConfigBuildHandler.BUILDER.comment("Offset on x-axis from screen center.").defineInRange("X-Offset", 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
			this.yOffset = ConfigBuildHandler.BUILDER.comment("Offset on y-axis from screen bottom.").defineInRange("Y-Offset", 18, 0, Integer.MAX_VALUE);

			BUILDER.pop();

		}

	}

	public static class SaveIconConfig {

		public final ForgeConfigSpec.IntValue xOffset;
		public final ForgeConfigSpec.IntValue yOffset;
		public final ForgeConfigSpec.EnumValue<PositionPreset> position;
		public final ForgeConfigSpec.IntValue displayTime;
		public final ForgeConfigSpec.BooleanValue potionShift;
		public final ForgeConfigSpec.BooleanValue showArrow;
		public final ForgeConfigSpec.BooleanValue rotatingModel;

		private SaveIconConfig(String name) {

			BUILDER.push(name);

			this.xOffset = ConfigBuildHandler.BUILDER.comment("Offset on x-axis from screen border.").defineInRange("X-Offset", 17, 0, Integer.MAX_VALUE);
			this.yOffset = ConfigBuildHandler.BUILDER.comment("Offset on y-axis from screen border.").defineInRange("Y-Offset", 15, 0, Integer.MAX_VALUE);
			this.position = ConfigBuildHandler.BUILDER.comment("Define a screen corner to display the save icon in.").defineEnum("Screen Corner", PositionPreset.TOP_RIGHT);
			this.displayTime = ConfigBuildHandler.BUILDER.comment("Amount of ticks the save icon will be displayed for. Set to 0 to always display the icon.").defineInRange("Display Time", 40, 0, Integer.MAX_VALUE);
			this.potionShift = ConfigBuildHandler.BUILDER.comment("Shift the save icon downwards when it would otherwise overlap with the potion icons. Only applicable when the \"Screen Corner\" is set to \"TOP_RIGHT\".").define("Potion Shift", true);
			this.showArrow = ConfigBuildHandler.BUILDER.comment("Show a downwards pointing, animated arrow above the save icon.").define("Show Arrow", true);
			this.rotatingModel = ConfigBuildHandler.BUILDER.comment("Use an animated chest model instead of the static texture.").define("Rotating Model", true);

			BUILDER.pop();

		}

	}

	public static class CoordinateDisplayConfig {

		public final ForgeConfigSpec.IntValue scale;
		public final ForgeConfigSpec.IntValue xOffset;
		public final ForgeConfigSpec.IntValue yOffset;
		public final ForgeConfigSpec.EnumValue<PositionPreset> position;
		public final ForgeConfigSpec.BooleanValue background;
		public final ForgeConfigSpec.IntValue decimalPlaces;

		private CoordinateDisplayConfig(String name) {

			BUILDER.push(name);

			this.scale = ConfigBuildHandler.BUILDER.comment("Scale of coordinate display. Works in tandem with \"GUI Scale\" option in \"Video Settings\".").defineInRange("Scale", 6, 1, 24);
			this.xOffset = ConfigBuildHandler.BUILDER.comment("Offset on x-axis from screen border.").defineInRange("X-Offset", 0, 0, Integer.MAX_VALUE);
			this.yOffset = ConfigBuildHandler.BUILDER.comment("Offset on y-axis from screen border.").defineInRange("Y-Offset", 60, 0, Integer.MAX_VALUE);
			this.position = ConfigBuildHandler.BUILDER.comment("Define a screen corner to show the coordinate display in.").defineEnum("Screen Corner", PositionPreset.TOP_LEFT);
			this.background = ConfigBuildHandler.BUILDER.comment("Show black chat background behind coordinate display for better visibility.").define("Draw Background", true);
			this.decimalPlaces = ConfigBuildHandler.BUILDER.comment("Amount of decimal places for the three coordinates.").defineInRange("Decimal Places", 0, 0, Integer.MAX_VALUE);

			BUILDER.pop();

		}

	}

	public static class MiscellaneousConfig {

		public final ForgeConfigSpec.BooleanValue elytraTilt;
		public final ForgeConfigSpec.DoubleValue elytraMultiplier;
		public final ForgeConfigSpec.BooleanValue sumShulkerBox;
		public final ForgeConfigSpec.IntValue shulkerBoxRows;
		public final ForgeConfigSpec.BooleanValue hideHudInGui;
		public final ForgeConfigSpec.BooleanValue closeButton;
		public final ForgeConfigSpec.IntValue closeButtonXOffset;
		public final ForgeConfigSpec.IntValue closeButtonYOffset;
		public final ForgeConfigSpec.BooleanValue tintedTooltip;

		private MiscellaneousConfig(String name) {

			BUILDER.push(name);

			this.elytraTilt = ConfigBuildHandler.BUILDER.comment("Tilt the camera according to elytra flight angle.").define("Tilt Elytra Camera", true);
			this.elytraMultiplier = ConfigBuildHandler.BUILDER.comment("Multiplier for the camera tilt when elytra flying.").defineInRange("Elytra Tilt Multiplier", 0.5, 0.1, 1.0);
			this.sumShulkerBox = ConfigBuildHandler.BUILDER.comment("Sum up stacks of equal items on the shulker box tooltip.").define("Sum Shulker Box Contents", true);
			this.shulkerBoxRows = ConfigBuildHandler.BUILDER.comment("Maximum amount of rows on the shulker box tooltip.").defineInRange("Shulker Box Rows", 6, 0, Integer.MAX_VALUE);
			this.hideHudInGui = ConfigBuildHandler.BUILDER.comment("Hide hud elements when inside of a container.").define("Hide Hud In Container", true);
			this.closeButton = ConfigBuildHandler.BUILDER.comment("Add a button for closing to every container.").define("Close Button", true);
			this.closeButtonXOffset = ConfigBuildHandler.BUILDER.comment("Offset on x-axis from gui right.").defineInRange("Close Button X-Offset", 5, Integer.MIN_VALUE, Integer.MAX_VALUE);
			this.closeButtonYOffset = ConfigBuildHandler.BUILDER.comment("Offset on y-axis from gui top.").defineInRange("Close Button Y-Offset", 5, Integer.MIN_VALUE, Integer.MAX_VALUE);
			this.tintedTooltip = ConfigBuildHandler.BUILDER.comment("Make item tooltips colored similarly to Console Edition.").define("Tinted Tooltip", false);

			BUILDER.pop();

		}

	}

	public static final ForgeConfigSpec SPEC = BUILDER.build();
	
}