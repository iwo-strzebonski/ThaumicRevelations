package thaumrev.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import thaumrev.ThaumRevLibrary;

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

	/** Client-side **/

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {
		topIcon = register.registerIcon("thaumrev:infusedquartzpillartop");
		sideIcon = register.registerIcon("thaumrev:infusedquartzpillarside");
	}

	@Override
	@SideOnly(Side.CLIENT)
	protected IIcon getSideIcon(int par) {
		return sideIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	protected IIcon getTopIcon(int par) {
		return topIcon;
	}
}
