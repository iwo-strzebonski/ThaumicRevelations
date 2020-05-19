package thaumrev.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import thaumrev.common.ThaumRevLibrary;

public class BlockQuartzPillar extends BlockRotatedPillar {

	public IIcon topIcon;
	public IIcon sideIcon;

	public BlockQuartzPillar() {
		super(Material.rock);
		setBlockName("blockInfusedQuartzPillar");
		setCreativeTab(ThaumRevLibrary.tabThaumRev);
		setStepSound(Block.soundTypeStone);
		setHardness(0.8F);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister register) {
		topIcon = register.registerIcon("thaumrev:infusedquartzpillartop");
		sideIcon = register.registerIcon("thaumrev:infusedquartzpillarside");
	}

	@SideOnly(Side.CLIENT)
	protected IIcon getSideIcon(int par) {
		return sideIcon;
	}

	@SideOnly(Side.CLIENT)
	protected IIcon getTopIcon(int par) {
		return topIcon;
	}
}