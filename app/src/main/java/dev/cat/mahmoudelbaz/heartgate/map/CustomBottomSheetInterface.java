package dev.cat.mahmoudelbaz.heartgate.map;

import dev.cat.mahmoudelbaz.heartgate.myAccount.ModelMyConnections;

public interface CustomBottomSheetInterface {
    void callAddConnection(ModelMyConnections object);

    void cancelConnectionRequest(ModelMyConnections object);

    void disconnectConnectionRequest(ModelMyConnections object);
}
