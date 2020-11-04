package thaumrev.util.wardenic;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import thaumrev.item.ItemWardenBow;
import thaumrev.item.ItemWardenWeapon;
import thaumrev.item.armor.ItemWardenArmor;
import thaumrev.item.baubles.ItemWardenAmulet;

import java.util.Random;

public class WardenicChargeEvents {

	private final Random random = new Random();

	public static void init() {
		MinecraftForge.EVENT_BUS.register(new WardenicChargeEvents());
	}

	@SubscribeEvent
	public void onServerTick(LivingUpdateEvent event) {
		if (event.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entity;

			for (int i = 0; i < 5; i++) {
				if (player.getEquipmentInSlot(i) != null) {
					if (player.getEquipmentInSlot(i).getItem() instanceof ItemWardenArmor ||
							player.getEquipmentInSlot(i).getItem() instanceof ItemWardenWeapon ||
							player.getEquipmentInSlot(i).getItem() instanceof ItemWardenBow) {
						if (player.getEquipmentInSlot(i).getItemDamage() != player.getEquipmentInSlot(i).getMaxDamage()) {
							if (random.nextInt(50) == 0) {
								player.getEquipmentInSlot(i).setItemDamage(player.getEquipmentInSlot(i).getItemDamage() - 1);
							}
						}
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void onHurt(LivingHurtEvent event) {
		if (event.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entity;
			for (int i = 1; i < 5; i++) {
				if (player.getEquipmentInSlot(i) != null &&
					(player.getEquipmentInSlot(i).getItem() instanceof ItemWardenArmor)) {
						if (player.getEquipmentInSlot(i).getItemDamage() != player.getEquipmentInSlot(i).getMaxDamage()) {
							player.getEquipmentInSlot(i).setItemDamage(player.getEquipmentInSlot(i).getItemDamage() + 1);
							WardenicChargeHelper.getUpgrade(player.getEquipmentInSlot(i)).onAttacked(event);
						}
				}
			}
			++ItemWardenAmulet.amuletCharge;

		} else if ((event.source.getSourceOfDamage() instanceof EntityArrow) &&
				(event.source.getEntity() instanceof EntityPlayer)) {

			EntityPlayer player = (EntityPlayer)event.source.getEntity();
			Entity entityArrow = event.source.getSourceOfDamage();
			NBTTagCompound tag = entityArrow.getEntityData();

			if (tag.getBoolean("WardenArrow")) {
				WardenicChargeHelper.getUpgrade(player.getEquipmentInSlot(0)).onIndirectAttack(event);
			}
		}
	}
}