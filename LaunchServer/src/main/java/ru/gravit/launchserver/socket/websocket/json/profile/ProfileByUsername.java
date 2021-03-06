package ru.gravit.launchserver.socket.websocket.json.profile;

import io.netty.channel.ChannelHandlerContext;
import ru.gravit.launcher.events.request.ProfileByUsernameRequestEvent;
import ru.gravit.launchserver.LaunchServer;
import ru.gravit.launchserver.socket.Client;
import ru.gravit.launchserver.socket.websocket.WebSocketService;
import ru.gravit.launchserver.socket.websocket.json.JsonResponseInterface;

import java.util.UUID;

import static ru.gravit.launchserver.socket.websocket.json.profile.ProfileByUUIDResponse.getProfile;

public class ProfileByUsername implements JsonResponseInterface {
    String username;
    String client;
    @Override
    public String getType() {
        return "profileByUsername";
    }

    @Override
    public void execute(WebSocketService service, ChannelHandlerContext ctx, Client client) throws Exception {
        UUID uuid = client.auth.handler.usernameToUUID(username);
        service.sendObject(ctx, new ProfileByUsernameRequestEvent(getProfile(LaunchServer.server,uuid,username,this.client, client.auth.textureProvider)));
    }
}
