import socket

from f1_2019_telemetry.packets import unpack_udp_packet

def toString(packet):
    id = packet.header.packetId
    if id==1:
        #Session packet
        return "SessionPacket{" +\
                "packetHeader=" + headerToString(packet.header) +\
                ", weather=" + str(packet.weather) +\
                ", trackTemp=" + str(packet.trackTemperature) +\
                ", airTemp=" + str(packet.airTemperature) +\
                ", laps=" + str(packet.totalLaps) +\
                ", trackLength=" + str(packet.trackLength) +\
                ", sessionType=" + str(packet.sessionType) +\
                ", trackID=" + str(packet.trackId) +\
                ", formula=" + str(packet.m_formula) +\
                ", sessionTimeLeft=" + str(packet.sessionTimeLeft) +\
                ", sessionDuration=" + str(packet.sessionDuration) +\
                ", pitSpeedLim=" + str(packet.pitSpeedLimit) +\
                ", gamePaused=" + str(packet.gamePaused) +\
                ", isSpectating=" + str(packet.isSpectating) +\
                ", spectatorIndex=" + str(packet.spectatorCarIndex) +\
                ", sliSupport=" + str(packet.sliProNativeSupport) +\
                ", numMarshallZones=" + str(packet.numMarshalZones) +\
                ", marshallZones=" + marshallZonesToString(packet.numMarshalZones, packet.marshalZones) +\
                ", safteyCarStatus=" + str(packet.safetyCarStatus) +\
                ", networkedGame=" + str(packet.networkGame) +\
                '}'
    elif id==2:
        #Lap data packet
        pass
    elif id==6:
        #Car telemetry packet
        pass
    elif id==7:
        #Car status packet
        pass
    else:
        pass

def headerToString(header):
    return "PacketHeader{" +\
                "packetFormat=" + str(header.packetFormat) +\
                ", gameMajorVersion=" + str(header.gameMajorVersion) +\
                ", gameMinorVersion=" + str(header.gameMinorVersion) +\
                ", packetVersion=" + str(header.packetVersion) +\
                ", packetID=" + str(header.packetId)+\
                ", sessionUID=" + str(header.sessionUID) +\
                ", sessionTime=" + str(header.sessionTime) +\
                ", frameID=" + str(header.frameIdentifier) +\
                ", playerCarIndex=" + str(header.playerCarIndex) +\
                '}'

def marshallZonesToString(num, zones):
    string = ""
    for i in range(0, num):
        if(i==num):
            string = string + "MarshallZone{" + "zoneStart=" + str(zones[i].zoneStart) + ", zoneFlag=" + str(zones[i].zoneFlag) + '}, '
        else:
            string = string + "MarshallZone{" + "zoneStart=" + str(zones[i].zoneStart) + ", zoneFlag=" + str(zones[i].zoneFlag) + '}, '
    return string

udp_socket = socket.socket(family=socket.AF_INET, type=socket.SOCK_DGRAM)
udp_socket.bind(('', 20777))

while True:
    udp_packet = udp_socket.recv(2048)
    packet = unpack_udp_packet(udp_packet)
    print(toString(packet))
