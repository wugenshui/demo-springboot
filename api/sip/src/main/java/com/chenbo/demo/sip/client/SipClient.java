package com.github.wugenshui.sip.client;

import lombok.extern.slf4j.Slf4j;

import javax.sip.Dialog;
import javax.sip.InvalidArgumentException;
import javax.sip.ListeningPoint;
import javax.sip.ObjectInUseException;
import javax.sip.PeerUnavailableException;
import javax.sip.SipException;
import javax.sip.SipFactory;
import javax.sip.SipProvider;
import javax.sip.SipStack;
import javax.sip.TransportNotSupportedException;
import javax.sip.address.Address;
import javax.sip.address.AddressFactory;
import javax.sip.address.SipURI;
import javax.sip.header.CSeqHeader;
import javax.sip.header.CallIdHeader;
import javax.sip.header.ContactHeader;
import javax.sip.header.ExpiresHeader;
import javax.sip.header.FromHeader;
import javax.sip.header.HeaderFactory;
import javax.sip.header.MaxForwardsHeader;
import javax.sip.header.ToHeader;
import javax.sip.header.ViaHeader;
import javax.sip.message.MessageFactory;
import javax.sip.message.Request;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.TooManyListenersException;

/**
 * sip 客户端
 *
 * @author : chenbo
 * @date : 2020-07-02
 */
@Slf4j
public class SipClient {

    private SipStack sipStack;

    private SipFactory sipFactory;

    private AddressFactory addressFactory;

    private HeaderFactory headerFactory;

    private MessageFactory messageFactory;

    private SipProvider sipProvider;

    private Dialog dialog;

    String ip = "192.168.1.47";
    int port = 5061;
    String uname = "Tom";

    public static void main(String[] args) {
        SipClient client = new SipClient();
        client.init();
        client.sendMessage("test_sender", "192.168.1.47:5061", "server", "192.168.1.47:5061", "");
    }

    public void init() {
        try {
            Properties prop = new Properties();
            prop.setProperty("javax.sip.STACK_NAME", "teststackname");
            prop.setProperty("javax.sip.IP_ADDRESS", ip);
            prop.setProperty("gov.nist.javax.sip.TRACE_LEVEL", "32");
            // prop.setProperty("gov.nist.javax.sip.DEBUG_LOG", "sipclientdebug.txt");
            // prop.setProperty("gov.nist.javax.sip.SERVER_LOG", "sipclientlog.txt");

            sipFactory = SipFactory.getInstance();
            sipFactory.setPathName("gov.nist");

            sipStack = sipFactory.createSipStack(prop);

            headerFactory = sipFactory.createHeaderFactory();
            addressFactory = sipFactory.createAddressFactory();
            messageFactory = sipFactory.createMessageFactory();

            ListeningPoint listeningpoint_udp = sipStack.createListeningPoint(port, "udp");
//			ListeningPoint listeningponit_tcp =sipStack.createListeningPoint(port, "tcp");

            sipProvider = sipStack.createSipProvider(listeningpoint_udp);
            ClientListener listener = new ClientListener(addressFactory, headerFactory, messageFactory, sipProvider);
            sipProvider.addSipListener(listener);
//			sipProvider = sipStack.createSipProvider(listeningponit_tcp);
//			sipProvider.addSipListener(listener);
            log.info("client init finished.");
        } catch (PeerUnavailableException | TransportNotSupportedException | ObjectInUseException
                | InvalidArgumentException | TooManyListenersException e) {
            e.printStackTrace();
        }

    }

    /**
     * 发送消息
     *
     * @param fromUserName 发送者
     * @param fromIpPort   发送者ip
     * @param toUserName   接收者
     * @param toIpPort     接收者ip
     * @param message      消息内容
     */
    public void sendMessage(String fromUserName, String fromIpPort, String toUserName, String toIpPort, String message) {
        try {
            // callid
            CallIdHeader callIdHeader = sipProvider.getNewCallId();
            // cseq
            CSeqHeader cSeqHeader = headerFactory.createCSeqHeader(1L, Request.REGISTER);
            // from
            SipURI fromSipURI = addressFactory.createSipURI(fromUserName, fromIpPort);
            Address fromAddress = addressFactory.createAddress(fromSipURI);
            fromAddress.setDisplayName(fromUserName);
            FromHeader fromHeader = headerFactory.createFromHeader(fromAddress, "mytag");
            // to
            SipURI toSipURI = addressFactory.createSipURI(toUserName, toIpPort);
            Address toAddress = addressFactory.createAddress(toSipURI);
            toAddress.setDisplayName(toUserName);
            ToHeader toHeader = headerFactory.createToHeader(toAddress, null);
            // via
            ViaHeader viaHeader = headerFactory.createViaHeader(ip, port, "udp", "branchingbranching");
            List<ViaHeader> viaHeaderList = new ArrayList<>();
            viaHeaderList.add(viaHeader);
            // maxforwards
            MaxForwardsHeader maxForwardsHeader = headerFactory.createMaxForwardsHeader(70);

            // 请求构造
            SipURI requestSipURI = addressFactory.createSipURI("gov.nist", "192.168.1.47:5060");
            requestSipURI.setTransportParam("udp");
            Request request = messageFactory.createRequest(requestSipURI, Request.REGISTER, callIdHeader, cSeqHeader, fromHeader, toHeader, viaHeaderList, maxForwardsHeader);

            // contact
            SipURI contactURI = addressFactory.createSipURI(fromUserName, fromIpPort);
            contactURI.setPort(port);
            Address contactAddress = addressFactory.createAddress(contactURI);
            contactAddress.setDisplayName(uname);
            ContactHeader contactHeader = headerFactory.createContactHeader(contactAddress);
            request.addHeader(contactHeader);

            // expires
            ExpiresHeader expiresHeader = headerFactory.createExpiresHeader(3600);
            request.addHeader(expiresHeader);

            // ContentTypeHeader contentTypeHeader = headerFactory.createContentTypeHeader("text","plain");
            // request.setContent(message,contentTypeHeader);

            log.info(request.toString());
            sipProvider.sendRequest(request);

            // ClientTransaction trans = sipProvider.getNewClientTransaction(request);
            // dialog = trans.getDialog();
            // trans.sendRequest();

            // request = dialog.createRequest(Request.MESSAGE);
            // request.setHeader(contactHeader);
            // request.setContent(message, contentTypeHeader);
            // ClientTransaction ctrans = sipProvider.getNewClientTransaction(request);
            // ctrans.sendRequest();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (InvalidArgumentException e) {
            e.printStackTrace();
        } catch (SipException e) {
            e.printStackTrace();
        }
    }
}

