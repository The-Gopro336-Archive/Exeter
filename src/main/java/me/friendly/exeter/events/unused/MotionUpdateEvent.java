package me.friendly.exeter.events.unused;

import me.friendly.api.event.Event;

public class MotionUpdateEvent
extends Event {
    private double positionX;
    private double oldPositionY;
    private double positionY;
    private double positionZ;
    private boolean sprinting;
    private boolean onGround;
    private boolean sneaking;
    private boolean lockview = false;
    private float oldRotationYaw;
    private float rotationYaw;
    private float oldRotationPitch;
    private float rotationPitch;
    private Time time;

    public MotionUpdateEvent(Time time, double positionX, double positionY, double positionZ, boolean sprinting, boolean onGround, boolean sneaking, float rotationYaw, float rotationPitch) {
        this.time = time;
        this.positionX = positionX;
        this.positionY = this.oldPositionY = positionY;
        this.positionZ = positionZ;
        this.sprinting = sprinting;
        this.onGround = onGround;
        this.sneaking = sneaking;
        this.rotationYaw = this.oldRotationYaw = rotationYaw;
        this.rotationPitch = this.oldRotationPitch = rotationPitch;
    }

    public MotionUpdateEvent(Time time) {
        this.time = time;
    }

    public double getPositionX() {
        return this.positionX;
    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public double getPositionY() {
        return this.positionY;
    }

    public double getOldPositionY() {
        return this.oldPositionY;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }

    public double getPositionZ() {
        return this.positionZ;
    }

    public void setPositionZ(double positionZ) {
        this.positionZ = positionZ;
    }

    public boolean isSprinting() {
        return this.sprinting;
    }

    public void setSprinting(boolean sprinting) {
        this.sprinting = sprinting;
    }

    public boolean isOnGround() {
        return this.onGround;
    }

    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }

    public boolean isSneaking() {
        return this.sneaking;
    }

    public void setSneaking(boolean sneaking) {
        this.sneaking = sneaking;
    }

    public float getRotationYaw() {
        return this.rotationYaw;
    }

    public void setRotationYaw(float rotationYaw) {
        this.rotationYaw = rotationYaw;
    }

    public float getOldRotationPitch() {
        return this.oldRotationPitch;
    }

    public float getRotationPitch() {
        return this.rotationPitch;
    }

    public float getOldRotationYaw() {
        return this.oldRotationYaw;
    }

    public void setRotationPitch(float rotationPitch) {
        this.rotationPitch = rotationPitch;
    }

    public boolean isLockview() {
        return this.lockview;
    }

    public void setLockview(boolean lockview) {
        this.lockview = lockview;
    }

    public Time getTime() {
        return this.time;
    }

    public static enum Time {
        BEFORE,
        AFTER;

    }
}

