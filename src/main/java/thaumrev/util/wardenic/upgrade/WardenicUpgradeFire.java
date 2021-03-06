package thaumrev.util.wardenic.upgrade;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.config.Config;
import thaumrev.util.wardenic.WardenicChargeHelper;

public class WardenicUpgradeFire extends WardenicUpgrade {

	public WardenicUpgradeFire(Aspect aspect) {
		super(aspect);
	}

	@Override
	public void onIndirectAttack(LivingHurtEvent event) {
		super.onIndirectAttack(event);

		Entity entity = event.entity;
		EntityPlayer player = (EntityPlayer)event.source.getEntity();
		EntityArrow entityArrow = (EntityArrow)event.source.getSourceOfDamage();

		int count = 0;

		for (int i = 0; i < 4; i++) {
			if ((player.getCurrentArmor(i) != null) &&
					WardenicChargeHelper.getUpgrade(player.getCurrentArmor(i)).getUpgradeAspect()
							.equals(Aspect.FIRE.getName())) {
				count++;
			}
		}

		if (entityArrow.getIsCritical()) {
			entity.setFire(4 * (count + 1));
		}
	}

	@Override
	public void onAttack(ItemStack stack, EntityPlayer player, Entity entity) {
		super.onAttack(stack, player, entity);

		int count = 0;

		for (int i = 0; i < 4; i++) {
			if ((player.getCurrentArmor(i) != null) &&
					WardenicChargeHelper.getUpgrade(player.getCurrentArmor(i)).getUpgradeAspect()
							.equals(Aspect.FIRE.getName())) {
				count++;
			}
		}

		entity.setFire(4 * (count + 1));
	}

	@Override
	public void onAttacked(LivingHurtEvent event) {
		super.onAttacked(event);

		int count = 0;
		EntityPlayer player = (EntityPlayer) event.entity;

		for (int i = 0; i <= 3; i++) {
			if ((player.getCurrentArmor(i) != null) &&
					WardenicChargeHelper.getUpgrade(player.getCurrentArmor(i)).getUpgradeAspect()
							.equals(Aspect.FIRE.getName())) {
				count++;
			}
		}

		if (event.source.getEntity() != null) {
			event.source.getEntity().setFire(2);
		}

		if (event.source.isFireDamage()) {
			event.ammount *= 1 - (0.25F * count);
		} else if (event.source.isExplosion()) {
			event.ammount *= 1 - (0.15F * count);
		}
	}

	@Override
	public void onTick(World world, EntityPlayer player, ItemStack stack) {
		super.onTick(world, player, stack);

		int count = 0;

		if (player.isBurning()) {
			player.extinguish();
		}

		for (int i = 0; i <= 3; i++) {
			if ((player.getCurrentArmor(i) != null) &&
					WardenicChargeHelper.getUpgrade(player.getCurrentArmor(i)).getUpgradeAspect()
							.equals(Aspect.FIRE.getName())) {
				count++;
			}
		}

		if (count == 4) {
			if (player.isPotionActive(Config.potionSunScornedID)) {
				player.removePotionEffect(Config.potionSunScornedID);
			}
		}
	}
}
