package net.mars_myth.creations.compat;


import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.mars_myth.creations.CreationsBeyond;
import net.mars_myth.creations.init.ModBlocks;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.LinkedList;
import java.util.List;

public class InfuserCatagory implements DisplayCategory<BasicDisplay> {
    public static final Identifier TEXTURE = Identifier.of(CreationsBeyond.MOD_ID, "textures/gui/infuser/infuser_gui.png");
    public static final CategoryIdentifier<InfuserDisplay> INFUSER =
            CategoryIdentifier.of(CreationsBeyond.MOD_ID, "infuser");

    @Override
    public CategoryIdentifier<? extends BasicDisplay> getCategoryIdentifier() {
        return INFUSER;
    }

    @Override
    public Text getTitle() {
        return Text.literal("Infuser");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ModBlocks.INFUSER.asItem().getDefaultStack());
    }

    @Override
    public List<Widget> setupDisplay(BasicDisplay display, Rectangle bounds) {
        Point startPoint = new Point(bounds.getCenterX() - 87, bounds.getCenterY() - 35);
        List<Widget> widgets = new LinkedList<>();

        widgets.add(Widgets.createTexturedWidget(TEXTURE, new Rectangle(startPoint.x, startPoint.y, 175, 82)));

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 54, startPoint.y + 18))
                .entries(display.getInputEntries().get(0)).markInput());

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 54, startPoint.y + 51))
                .entries(display.getInputEntries().get(0)).markInput());

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 104, startPoint.y + 34))
                .entries(display.getOutputEntries().get(0)).markOutput());

        return widgets;
    }

    @Override
    public int getDisplayHeight() {
        return 90;
    }
}