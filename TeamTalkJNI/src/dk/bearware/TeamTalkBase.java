/*
 * Copyright (c) 2005-2014, BearWare.dk
 * 
 * Contact Information:
 *
 * Bjoern D. Rasmussen
 * Skanderborgvej 40 4-2
 * DK-8000 Aarhus C
 * Denmark
 * Email: contact@bearware.dk
 * Phone: +45 20 20 54 59
 * Web: http://www.bearware.dk
 *
 * This source code is part of the TeamTalk 5 SDK owned by
 * BearWare.dk. All copyright statements may not be removed 
 * or altered from any source distribution. If you use this
 * software in a product, an acknowledgment in the product 
 * documentation is required.
 *
 */

package dk.bearware;

import java.util.Vector;

public abstract class TeamTalkBase
{
    private long ttInst = 0;

    public static native String getVersion();

    private native long initTeamTalkPoll();

    public TeamTalkBase() {
        ttInst = initTeamTalkPoll();
    }
    
    protected void finalize( ) throws Throwable {
        closeTeamTalk(ttInst);
    }

    private native boolean closeTeamTalk(long lpTTInstance);
    public boolean closeTeamTalk() {
        return closeTeamTalk(ttInst);
    }

    private native boolean getMessage(long lpTTInstance,
                                      TTMessage pMsg,
                                      int pnWaitMs);
    public boolean getMessage(TTMessage pMsg,
                              int pnWaitMs) {
        return getMessage(ttInst, pMsg, pnWaitMs);
    }

    private native int getFlags(long lpTTInstance);
    public int getFlags() { return getFlags(ttInst); }

    public static native boolean setLicenseInformation(String szRegName,
                                                       String szRegKey);

    public static native boolean getDefaultSoundDevices(IntPtr lpnInputDeviceID,
                                                        IntPtr lpnOutputDeviceID);
    
    private static native boolean getSoundDevices(SoundDevice[] lpSoundDevices,
                                                  IntPtr lpnHowMany);
    public static boolean getSoundDevices(Vector<SoundDevice> lpSoundDevices) {
        IntPtr lpnHowMany = new IntPtr();
        if(!getSoundDevices(null, lpnHowMany))
            return false;

        SoundDevice[] devs = new SoundDevice[lpnHowMany.value];
        if(getSoundDevices(devs, lpnHowMany))
        {
            for(int i=0;i<lpnHowMany.value;i++)
                lpSoundDevices.add(devs[i]);
        }
        return true;
    }


    public static native boolean restartSoundSystem();

    public static native long startSoundLoopbackTest(int nInputDeviceID, 
                                                     int nOutputDeviceID,
                                                     int nSampleRate,
                                                     int nChannels,
                                                     boolean bDuplexMode,
                                                     AudioConfig lpAudioConfig);

    public static native boolean closeSoundLoopbackTest(long lpTTSoundLoop);

    private native boolean initSoundInputDevice(long lpTTInstance, int nInputDeviceID);
    public boolean initSoundInputDevice(int nInputDeviceID) {
        return initSoundInputDevice(ttInst, nInputDeviceID);
    }
    private native boolean initSoundOutputDevice(long lpTTInstance, int nOutputDeviceID);
    public boolean initSoundOutputDevice(int nOutputDeviceID) {
        return initSoundOutputDevice(ttInst, nOutputDeviceID);
    }
    private native boolean closeSoundInputDevice(long lpTTInstance);
    public boolean closeSoundInputDevice() {
        return closeSoundInputDevice(ttInst);
    }
    private native boolean closeSoundOutputDevice(long lpTTInstance);
    public boolean closeSoundOutputDevice() {
        return closeSoundOutputDevice(ttInst);
    }

    private native int getSoundInputLevel(long lpTTInstance);
    public int getSoundInputLevel() { return getSoundInputLevel(ttInst); }

    private native boolean setSoundInputGainLevel(long lpTTInstance, int nLevel);
    public boolean setSoundInputGainLevel(int nLevel)
        { return setSoundInputGainLevel(ttInst, nLevel); }

    private native int getSoundInputGainLevel(long lpTTInstance);
    public int getSoundInputGainLevel() { return getSoundInputGainLevel(ttInst); }

    private native boolean setAudioConfig(long lpTTInstance, AudioConfig lpAudioConfig);
    public boolean setAudioConfig(AudioConfig lpAudioConfig) {
        return setAudioConfig(ttInst, lpAudioConfig);
    }

    private native boolean getAudioConfig(long lpTTInstance, AudioConfig lpAudioConfig);
    public boolean getAudioConfig(AudioConfig lpAudioConfig) {
        return getAudioConfig(ttInst, lpAudioConfig);
    }

    private native boolean setSoundOutputVolume(long lpTTInstance, int nVolume);
    public boolean setSoundOutputVolume(int nVolume)
        { return setSoundOutputVolume(ttInst, nVolume); }

    private native int getSoundOutputVolume(long lpTTInstance);
    public int getSoundOutputVolume()
        { return getSoundOutputVolume(ttInst); }

    private native boolean setSoundOutputMute(long lpTTInstance, boolean bMuteAll);
    public boolean setSoundOutputMute(boolean bMuteAll)
        { return setSoundOutputMute(ttInst, bMuteAll); }

    private native boolean enable3DSoundPositioning(long lpTTInstance,
                                                    boolean bEnable);
    public boolean enable3DSoundPositioning(boolean bEnable) {
        return enable3DSoundPositioning(ttInst, bEnable);
    }
    
    private native boolean autoPositionUsers(long lpTTInstance);
    public boolean autoPositionUsers() { return autoPositionUsers(ttInst); }

    private native boolean enableAudioBlockEvent(long lpTTInstance,
                                                 boolean bEnable);
    public boolean enableAudioBlockEvent(boolean bEnable)
        { return enableAudioBlockEvent(ttInst, bEnable); }

    private native boolean enableVoiceTransmission(long lpTTInstance, 
                                                   boolean bEnable);
    public boolean enableVoiceTransmission(boolean bEnable) {
        return enableVoiceTransmission(ttInst, bEnable);
    }

    private native boolean enableVoiceActivation(long lpTTInstance,
                                                 boolean bEnable);
    public boolean enableVoiceActivation(boolean bEnable)
        { return enableVoiceActivation(ttInst, bEnable); }
    
    private native boolean setVoiceActivationLevel(long lpTTInstance,
                                                   int nLevel);
    public boolean setVoiceActivationLevel(int nLevel)
        { return setVoiceActivationLevel(nLevel); }

    private native int getVoiceActivationLevel(long lpTTInstance);
    public int getVoiceActivationLevel()
        { return getVoiceActivationLevel(ttInst); }

    private native boolean setVoiceActivationStopDelay(long lpTTInstance,
                                                       int nDelayMSec);
    public boolean setVoiceActivationStopDelay(int nDelayMSec)
        { return setVoiceActivationStopDelay(nDelayMSec); }

    private native int getVoiceActivationStopDelay(long lpTTInstance);
    public int getVoiceActivationStopDelay()
        { return getVoiceActivationStopDelay(ttInst); }

    /** TODO: not implemented
    private native boolean startVideoCaptureTransmission(long lpTTInstance,
                                                         VideoCodec lpVideoCodec);
    public boolean startVideoCaptureTransmission(VideoCodec lpVideoCodec) {
        return startVideoCaptureTransmission(ttInst, lpVideoCodec);
    }

    private native boolean stopVideoCaptureTransmission(long lpTTInstance);
    public boolean stopVideoCaptureTransmission() {
        return stopVideoCaptureTransmission(ttInst);
    }

    private native boolean startStreamingMediaFileToChannel(long lpTTInstance,
                                                            String szMediaFilePath,
                                                            VideoCodec lpVideoCodec);
    public boolean startStreamingMediaFileToChannel(String szMediaFilePath,
                                                    VideoCodec lpVideoCodec) {
        return startStreamingMediaFileToChannel(ttInst, szMediaFilePath,
                                                lpVideoCodec);
    }

    private native boolean stopStreamingMediaFileToChannel(long lpTTInstance);
    public boolean stopStreamingMediaFileToChannel() {
        return stopStreamingMediaFileToChannel(ttInst);
    }

    public static native boolean getMediaFileInfo(String szMediaFilePath,
                                                  MediaFileInfo lpMediaFileInfo);

    private native VideoFrame acquireUserMediaVideoFrame(long lpTTInstance,
                                                         int nUserID);
    public VideoFrame acquireUserMediaVideoFrame(int nUserID) {
        return acquireUserMediaVideoFrame(ttInst, nUserID);
    }

    private native boolean releaseUserMediaVideoFrame(long lpTTInstance,
                                                      int nUserID);
    public boolean releaseUserMediaVideoFrame(int nUserID) {
        return releaseUserMediaVideoFrame(ttInst, nUserID);
    }

    private native boolean sendDesktopCursorPosition(long lpTTInstance,
                                                     int nPosX, int nPosY);
    public boolean sendDesktopCursorPosition(int nPosX, int nPosY) {
        return sendDesktopCursorPosition(ttInst, nPosX, nPosY);
    }

    private native boolean sendDesktopInput(long lpTTInstance,
                                            int nUserID,
                                            DesktopInput[] lpDesktopInputs);
    public boolean sendDesktopInput(int nUserID,
                                    DesktopInput[] lpDesktopInputs) {
        return sendDesktopInput(ttInst, nUserID, lpDesktopInputs);
    }
    */

    private native boolean connect(long lpTTInstance,
                                   String szHostAddress,
                                   int nTcpPort, 
                                   int nUdpPort, 
                                   int nLocalTcpPort, 
                                   int nLocalUdpPort,
                                   boolean bEncrypted);
    public boolean connect(String szHostAddress,
                           int nTcpPort, 
                           int nUdpPort, 
                           int nLocalTcpPort, 
                           int nLocalUdpPort,
                           boolean bEncrypted) {
        return connect(ttInst, szHostAddress, nTcpPort, nUdpPort, 
                       nLocalTcpPort, nLocalUdpPort, bEncrypted);
    }
    
    private native boolean connectEx(long lpTTInstance,
                                     String szHostAddress,
                                     int nTcpPort, 
                                     int nUdpPort, 
                                     String szBindIPAddr,
                                     int nLocalTcpPort, 
                                     int nLocalUdpPort,
                                     boolean bEncrypted);
    public boolean connectEx(String szHostAddress,
                             int nTcpPort, 
                             int nUdpPort, 
                             String szBindIPAddr,
                             int nLocalTcpPort, 
                             int nLocalUdpPort,
                             boolean bEncrypted) {
        return connectEx(ttInst, szHostAddress, nTcpPort, nUdpPort, 
                         szBindIPAddr, nLocalTcpPort, nLocalUdpPort,
                         bEncrypted);
    }
    private native boolean disconnect(long lpTTInstance);
    public boolean disconnect() {
        return disconnect(ttInst);
    }
    private native boolean queryMaxPayload(long lpTTInstance, int nUserID);
    public boolean queryMaxPayload(int nUserID) {
        return queryMaxPayload(ttInst, nUserID);
    }

    private native boolean getClientStatistics(long lpTTInstance, ClientStatistics lpClientStatistics);
    public boolean getClientStatistics(ClientStatistics lpClientStatistics) {
        return getClientStatistics(ttInst, lpClientStatistics);
    }

    private native int doPing(long lpTTInstance);
    public int doPing() {
        return doPing(ttInst);
    }

    private native int doLogin(long lpTTInstance,
                              String szNickname, 
                              String szUsername,
                              String szPassword);
    public int doLogin(String szNickname, 
                       String szUsername,
                       String szPassword) {
        return doLogin(ttInst, szNickname,
                       szUsername, szPassword);
    }
    private native int doLogout(long lpTTInstance);
    public int doLogout() { return doLogout(ttInst); }

    private native int doJoinChannel(long lpTTInstance,
                                     Channel lpChannel);
    public int doJoinChannel(Channel lpChannel) {
        return doJoinChannel(ttInst, lpChannel);
    }
    private native int doJoinChannelByID(long lpTTInstance,
                                         int nChannelID, 
                                         String szPassword);
    public int doJoinChannelByID(int nChannelID, 
                                 String szPassword) {
        return doJoinChannelByID(ttInst, nChannelID, szPassword);
    }
    private native int doLeaveChannel(long lpTTInstance);
    public int doLeaveChannel() { 
        return doLeaveChannel(ttInst);
    }
    private native int doChangeNickname(long lpTTInstance,
                                        String szNewNick);
    public int doChangeNickname(String szNewNick) { 
        return doChangeNickname(ttInst, szNewNick);
    }
    private native int doChangeStatus(long lpTTInstance, int nStatusMode, String szStatusMessage);
    public int doChangeStatus(int nStatusMode, String szStatusMessage) {
        return doChangeStatus(ttInst, nStatusMode, szStatusMessage);
    }
    private native int doTextMessage(long lpTTInstance, TextMessage lpTextMessage);
    public int doTextMessage(TextMessage lpTextMessage) {
        return doTextMessage(ttInst, lpTextMessage);
    }
    private native int doChannelOp(long lpTTInstance, int nUserID, int nChannelID, boolean bMakeOperator);
    public int doChannelOp(int nUserID, int nChannelID, boolean bMakeOperator) {
        return doChannelOp(ttInst, nUserID, nChannelID, bMakeOperator);
    }
    private native int doChannelOpEx(long lpTTInstance, int nUserID, int nChannelID, 
                                     String szOpPassword,boolean bMakeOperator);
    public int doChannelOpEx(int nUserID, int nChannelID, 
                             String szOpPassword, boolean bMakeOperator) {
        return doChannelOpEx(ttInst, nUserID, nChannelID, szOpPassword, bMakeOperator);
    }
    private native int doKickUser(long lpTTInstance, int nUserID, int nChannelID);
    public int doKickUser(int nUserID, int nChannelID) {
        return doKickUser(ttInst, nUserID, nChannelID);
    }
    private native int doSendFile(long lpTTInstance, int nChannelID, String szLocalFilePath);
    public int doSendFile(int nChannelID, String szLocalFilePath) {
        return doSendFile(ttInst, nChannelID, szLocalFilePath);
    }
    private native int doRecvFile(long lpTTInstance, int nChannelID, int nFileID,
                                  String szLocalFilePath);
    public int doRecvFile(int nChannelID, int nFileID,
                          String szLocalFilePath) {
        return doRecvFile(ttInst, nChannelID, nFileID, szLocalFilePath);
    }
    private native int doDeleteFile(long lpTTInstance, int nChannelID, int nFileID);
    public int doDeleteFile(int nChannelID, int nFileID) {
        return doDeleteFile(ttInst, nChannelID, nFileID);
    }
    private native int doSubscribe(long lpTTInstance, int uSubscriptions);
    public int doSubscribe(int uSubscriptions) {
        return doSubscribe(ttInst, uSubscriptions);
    }
    private native int doUnsubscribe(long lpTTInstance, int uSubscriptions);
    public int doUnsubscribe(int uSubscriptions) {
        return doUnsubscribe(ttInst, uSubscriptions);
    }
    private native int doMakeChannel(long lpTTInstance, Channel lpChannel);
    public int doMakeChannel(Channel lpChannel) {
        return doMakeChannel(ttInst, lpChannel);
    }
    private native int doUpdateChannel(long lpTTInstance, Channel lpChannel);
    public int doUpdateChannel(Channel lpChannel) {
        return doUpdateChannel(ttInst, lpChannel);
    }
    private native int doRemoveChannel(long lpTTInstance, int nChannelID);
    public int doRemoveChannel(int nChannelID) {
        return doRemoveChannel(ttInst, nChannelID);
    }
    private native int doUpdateServer(long lpTTInstance, ServerProperties lpServerProperties);
    public int doUpdateServer(ServerProperties lpServerProperties) {
        return doUpdateServer(ttInst, lpServerProperties);
    }
    private native int doListUserAccounts(long lpTTInstance, int nIndex, int nCount);
    public int doListUserAccounts(int nIndex, int nCount) {
        return doListUserAccounts(ttInst, nIndex, nCount);
    }
    private native int doNewUserAccount(long lpTTInstance, UserAccount lpUserAccount);
    public int doNewUserAccount(UserAccount lpUserAccount) {
        return doNewUserAccount(ttInst, lpUserAccount);
    }
    private native int doDeleteUserAccount(long lpTTInstance, String szUsername);
    public int doDeleteUserAccount(String szUsername) {
        return doDeleteUserAccount(ttInst, szUsername);
    }
    private native int doBanUser(long lpTTInstance, int nUserID);
    public int doBanUser(int nUserID) {
        return doBanUser(ttInst, nUserID);
    }
    private native int doBanIPAddress(long lpTTInstance, String szIPAddress);
    public int doBanIPAddress(String szIPAddress) {
        return doBanIPAddress(ttInst, szIPAddress);
    }
    private native int doUnBanUser(long lpTTInstance, String szIPAddress);
    public int doUnBanUser(String szIPAddress) {
        return doUnBanUser(ttInst, szIPAddress);
    }
    private native int doListBans(long lpTTInstance, int nIndex, int nCount);
    public int doListBans(int nIndex, int nCount) {
        return doListBans(ttInst, nIndex, nCount);
    }
    private native int doSaveConfig(long lpTTInstance);
    public int doSaveConfig() {
        return doSaveConfig(ttInst);
    }
    private native int doQueryServerStats(long lpTTInstance);
    public int doQueryServerStats() {
        return doQueryServerStats(ttInst);
    }
    private native int doQuit(long lpTTInstance);
    public int doQuit() {
        return doQuit(ttInst);
    }
    private native boolean getServerProperties(long lpTTInstance,
                                               ServerProperties lpServerProperties);
    public boolean getServerProperties(ServerProperties lpServerProperties)
        { return getServerProperties(ttInst, lpServerProperties); }
    private native boolean getServerUsers(long lpTTInstance,
                                          User[] lpUserIDs, IntPtr lpnHowMany);
    public boolean getServerUsers(User[] lpUsers, IntPtr lpnHowMany) {
        return getServerUsers(ttInst, lpUsers, lpnHowMany);
    }
    private native int getRootChannelID(long lpTTInstance);
    public int getRootChannelID() {
        return getRootChannelID(ttInst);
    }
    private native int getMyChannelID(long lpTTInstance);
    public int getMyChannelID() {
        return getMyChannelID(ttInst);
    }
    private native boolean getChannel(long lpTTInstance,
                                     int nChannelID,
                                     Channel lpChannel);
    public boolean getChannel(int nChannelID,
                              Channel lpChannel) {
        return getChannel(ttInst, nChannelID, lpChannel);
    }
    private native String getChannelPath(long lpTTInstance,
                                         int nChannelID);
    public String getChannelPath(int nChannelID) {
        return getChannelPath(ttInst, nChannelID);
    }
    private native int getChannelIDFromPath(long lpTTInstance,
                                            String szChannelPath);
    public int getChannelIDFromPath(String szChannelPath) {
        return getChannelIDFromPath(ttInst, szChannelPath);
    }
    private native boolean getChannelUsers(long lpTTInstance,
                                           int nChannelID,
                                           User[] lpUsers,
                                           IntPtr lpnHowMany);
    public boolean getChannelUsers(int nChannelID,
                                   User[] lpUsers,
                                   IntPtr lpnHowMany) {
        return getChannelUsers(ttInst, nChannelID, lpUsers, lpnHowMany);
    }

    private native boolean getChannelFiles(long lpTTInstance,
                                           int nChannelID,
                                           RemoteFile[] lpRemoteFiles,
                                           IntPtr lpnHowMany);
    public boolean getChannelFiles(int nChannelID,
                                   RemoteFile[] lpRemoteFiles,
                                   IntPtr lpnHowMany) {
        return getChannelFiles(ttInst, nChannelID, lpRemoteFiles, lpnHowMany);
    }

    private native boolean getChannelFile(long lpTTInstance,
                                          int nChannelID,
                                          int nFileID, 
                                          RemoteFile lpRemoteFile);
    public boolean getChannelFile(int nChannelID,
                                  int nFileID, 
                                  RemoteFile lpRemoteFile) {
        return getChannelFile(ttInst, nChannelID, nFileID, lpRemoteFile);
    }
    private native boolean isChannelOperator(long lpTTInstance,
                                      int nUserID,
                                      int nChannelID);
    public boolean isChannelOperator(int nUserID,
                                     int nChannelID) {
        return isChannelOperator(ttInst, nUserID, nChannelID);
    }

    private native boolean getServerChannels(long lpTTInstance,
                                             Channel[] lpChannels,
                                             IntPtr lpnHowMany);
    //TODO: return array instead of query
    public boolean getServerChannels(Channel[] lpChannels,
                                     IntPtr lpnHowMany) {
        return getServerChannels(ttInst, lpChannels, lpnHowMany);
    }

    private native int getMyUserID(long lpTTInstance);
    public int getMyUserID() {
        return getMyUserID(ttInst);
    }
    private native boolean getMyUserAccount(long lpTTInstance,
                                            UserAccount lpUserAccount);
    public boolean getMyUserAccount(UserAccount lpUserAccount) {
        return getMyUserAccount(ttInst, lpUserAccount);
    }
    private native boolean getUser(long lpTTInstance,
                                  int nUserID,
                                  User lpUser);
    public boolean getUser(int nUserID,
                           User lpUser) {
        return getUser(ttInst, nUserID, lpUser);
    }
    private native boolean getUserStatistics(long lpTTInstance,
                                             int nUserID,
                                             UserStatistics lpUserStatistics);
    public boolean getUserStatistics(int nUserID,
                           UserStatistics lpUserStatistics) {
        return getUserStatistics(ttInst, nUserID, lpUserStatistics);
    }

    private native boolean setUserVolume(long lpTTInstance,
                                         int nUserID,
                                         int nStreamType,
                                         int nVolume);
    public boolean setUserVolume(int nUserID,
                                 int nStreamType,
                                 int nVolume) {
        return setUserVolume(ttInst, nUserID, nStreamType, nVolume);
    }
    private native boolean setUserGainLevel(long lpTTInstance,
                                            int nUserID,
                                            int nStreamType,
                                            int nGainLevel);
    public boolean setUserGainLevel(int nUserID,
                                    int nStreamType,
                                    int nGainLevel) {
        return setUserGainLevel(ttInst, nUserID, nStreamType, nGainLevel);
    }
    private native boolean setUserMute(long lpTTInstance,
                                       int nUserID,
                                       int nStreamType,
                                       boolean bMute);
    public boolean setUserMute(int nUserID,
                               int nStreamType,
                               boolean bMute) {
        return setUserMute(ttInst, nUserID, nStreamType, bMute);
    }
    private native boolean setUserStoppedPlaybackDelay(long lpTTInstance,
                                                       int nUserID,
                                                       int nStreamType,
                                                       int nDelayMSec);
    public boolean setUserStoppedPlaybackDelay(int nUserID,
                                              int nStreamType,
                                              int nDelayMSec) {
        return setUserStoppedPlaybackDelay(ttInst, nUserID, nStreamType, nDelayMSec);
    }
    private native boolean setUserPosition(long lpTTInstance,
                                           int nUserID,
                                           int nStreamType,
                                           float x, float y, float z);
    public boolean setUserPosition(int nUserID,
                                   int nStreamType,
                                   float x, float y, float z) {
        return setUserPosition(ttInst, nUserID, nStreamType, x, y, z);
    }
    private native boolean setUserStereo(long lpTTInstance,
                                         int nUserID,
                                         int nStreamType,
                                         boolean bLeftSpeaker,
                                         boolean bRightSpeaker);
    public boolean setUserStereo(int nUserID,
                                 int nStreamType,
                                 boolean bLeftSpeaker,
                                 boolean bRightSpeaker) {
        return setUserStereo(ttInst, nUserID, nStreamType, bLeftSpeaker, bRightSpeaker);
    }
    private native boolean setUserAudioFolder(long lpTTInstance,
                                              int nUserID,
                                              String szFolderPath,
                                              String szFileNameVars,
                                              int uAFF);
    public boolean setUserAudioFolder(int nUserID,
                                      String szFolderPath,
                                      String szFileNameVars, int uAFF) {
        return setUserAudioFolder(ttInst, nUserID, szFolderPath, szFileNameVars, uAFF);
    }
    private native boolean setUserAudioStreamBufferSize(long lpTTInstance,
                                                        int nUserID, int uStreamType, int nMSec);
    public boolean setUserAudioStreamBufferSize(int nUserID, int uStreamType, int nMSec) {
        return setUserAudioStreamBufferSize(ttInst, nUserID, uStreamType, nMSec);
    }
    private native boolean getFileTransferInfo(long lpTTInstance,
                                               int nTransferID, FileTransfer lpFileTransfer);
    public boolean getFileTransferInfo(int nTransferID, FileTransfer lpFileTransfer) {
        return getFileTransferInfo(ttInst, nTransferID, lpFileTransfer);
    }
    private native boolean cancelFileTransfer(long lpTTInstance,
                                              int nTransferID);
    public boolean cancelFileTransfer(int nTransferID) {
        return cancelFileTransfer(ttInst, nTransferID);
    }
    private native boolean getBannedUsers(long lpTTInstance,
                                          BannedUser[] lpBannedUsers,
                                          IntPtr lpnHowMany);
    public boolean getBannedUsers(BannedUser[] lpBannedUsers,
                                  IntPtr lpnHowMany) {
        return getBannedUsers(ttInst, lpBannedUsers, lpnHowMany);
    }
    private native boolean getUserAccounts(long lpTTInstance,
                                           UserAccount[] lpUserAccounts,
                                           IntPtr lpnHowMany);
    public boolean getUserAccounts(UserAccount[] lpUserAccounts,
                                   IntPtr lpnHowMany) {
        return getUserAccounts(ttInst, lpUserAccounts, lpnHowMany);
    }
    public native String getErrorMessage(int nError);
}