package com.alexey.textmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.Identifier;

public class Textmod implements ModInitializer, ClientModInitializer
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
        return Identifier.fromNamespaceAndPath( "textmod", path );
    }



}
