package com.alexey.rainbowmc;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.Identifier;

public class RainbowMc implements ModInitializer, ClientModInitializer
{

    @Override
    public void onInitialize()
    {
    }
    @Override
    public void onInitializeClient()
    {
    }

    public static Identifier id( String path )
    {
        return Identifier.fromNamespaceAndPath( "rainbowmc", path );
    }



}
