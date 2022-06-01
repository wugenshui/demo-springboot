package com.github.wugenshui.sip.client;

import com.github.wugenshui.sip.MD5Util;
import lombok.extern.slf4j.Slf4j;

import javax.sip.DialogTerminatedEvent;
import javax.sip.IOExceptionEvent;
import javax.sip.InvalidArgumentException;
import javax.sip.RequestEvent;
import javax.sip.ResponseEvent;
import javax.sip.SipException;
import javax.sip.SipListener;
import javax.sip.SipProvider;
import javax.sip.TimeoutEvent;
import javax.sip.TransactionTerminatedEvent;
import javax.sip.address.Address;
import javax.sip.address.AddressFactory;
import javax.sip.address.SipURI;
import javax.sip.header.AuthorizationHeader;
import javax.sip.header.CSeqHeader;
import javax.sip.header.CallIdHeader;
import javax.sip.header.ContactHeader;
import javax.sip.header.ContentTypeHeader;
import javax.sip.header.ExpiresHeader;
import javax.sip.header.FromHeader;
import javax.sip.header.HeaderFactory;
import javax.sip.header.MaxForwardsHeader;
import javax.sip.header.ToHeader;
import javax.sip.header.ViaHeader;
import javax.sip.header.WWWAuthenticateHeader;
import javax.sip.message.MessageFactory;
import javax.sip.message.Request;
import javax.sip.message.Response;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : chenbo
 * @date : 2020-07-02
 */
@Slf4j
public class ClientListener implements SipListener {
    private static final String IP = "192.168.186.209";

    private AddressFactory addressFactory;

    private HeaderFactory headerFactory;

    private MessageFactory messageFactory;

    private SipProvider sipProvider;

    public ClientListener(AddressFactory addressFactory, HeaderFactory headerFactory, MessageFactory messageFactory, SipProvider sipProvider) {
        super();
        this.addressFactory = addressFactory;
        this.headerFactory = headerFactory;
        this.messageFactory = messageFactory;
        this.sipProvider = sipProvider;
    }

    /**
     * 处理请求
     *
     * @param requestEvent
     */
    @Override
    public void processRequest(RequestEvent requestEvent) {
        log.info("processRequest执行");
        Request request = requestEvent.getRequest();
        if (null == request) {
            log.info("requestEvent.getRequest() is null.");
            return;
        }

        log.info("request内容:", request);
    }

    /**
     * 处理响应
     *
     * @param responseEvent
     */
    @Override
    public void processResponse(ResponseEvent responseEvent) {
        log.info("processResponse执行");
        Response response = responseEvent.getResponse();
        if (null == response) {
            log.info("response is null.");
            return;
        }
        log.info("返回码:" + response.getStatusCode());
        log.info("Response is :" + response);
        WWWAuthenticateHeader wwwHeader = (WWWAuthenticateHeader) response.getHeader(WWWAuthenticateHeader.NAME);
        if (null != wwwHeader) {
            String realm = wwwHeader.getRealm();
            String nonce = wwwHeader.getNonce();
            String A1 = MD5Util.MD5("Tom:" + realm + ":12345678");
            String A2 = MD5Util.MD5("REGISTER:sip:servername@" + IP + ":5060");
            String resStr = MD5Util.MD5(A1 + ":" + nonce + ":" + A2);

            try {
                // requestURI
                SipURI requestSipURI = addressFactory.createSipURI("gov.nist", IP + ":5060");
                requestSipURI.setTransportParam("udp");
                // from
                SipURI fromSipURI = addressFactory.createSipURI("Tom", IP + ":5061");
                Address fromAddress = addressFactory.createAddress(fromSipURI);
                fromAddress.setDisplayName("a");
                FromHeader fromHeader = headerFactory.createFromHeader(fromAddress, "mytag2");
                // to
                SipURI toSipURI = addressFactory.createSipURI("Tom", IP + ":5061");
                Address toAddress = addressFactory.createAddress(toSipURI);
                toAddress.setDisplayName("b");
                ToHeader toHeader = headerFactory.createToHeader(toAddress, null);
                // via
                ViaHeader viaHeader = headerFactory.createViaHeader(IP, 5061, "udp", "branchingbranching");
                List<ViaHeader> viaHeaderList = new ArrayList<>();
                viaHeaderList.add(viaHeader);
                // callid,cseq,maxforwards
                CallIdHeader callIdHeader = sipProvider.getNewCallId();
                CSeqHeader cSeqHeader = headerFactory.createCSeqHeader(2L, Request.REGISTER);
                MaxForwardsHeader maxForwardsHeader = headerFactory.createMaxForwardsHeader(70);
                //
                Request request = messageFactory.createRequest(requestSipURI, Request.REGISTER, callIdHeader, cSeqHeader, fromHeader, toHeader, viaHeaderList, maxForwardsHeader);
                // contant
                SipURI contantURI = addressFactory.createSipURI("Tom", IP + ":5061");
                contantURI.setPort(5061);
                Address contantAddress = addressFactory.createAddress(contantURI);
                contantAddress.setDisplayName("abc");
                ContactHeader contactHeader = headerFactory.createContactHeader(contantAddress);
                request.addHeader(contactHeader);
                // expires
                ExpiresHeader expiresHeader = headerFactory.createExpiresHeader(3600);
                request.addHeader(expiresHeader);

                ContentTypeHeader contentTypeHeader = headerFactory.createContentTypeHeader("text", "plain");
                request.setContent("", contentTypeHeader);

                AuthorizationHeader aHeader = headerFactory.createAuthorizationHeader("Digest");
                aHeader.setUsername("Tom");
                aHeader.setRealm(realm);
                aHeader.setNonce(nonce);
                aHeader.setURI(fromSipURI);
                aHeader.setResponse(resStr);
                aHeader.setAlgorithm("MD5");
                request.addHeader(aHeader);

                log.info(request.toString());
                sipProvider.sendRequest(request);
            } catch (ParseException | InvalidArgumentException | SipException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void processTimeout(TimeoutEvent timeoutEvent) {
        // TODO Auto-generated method stub

    }

    @Override
    public void processIOException(IOExceptionEvent exceptionEvent) {
        // TODO Auto-generated method stub

    }

    @Override
    public void processTransactionTerminated(TransactionTerminatedEvent transactionTerminatedEvent) {
        log.info("processTransactionTerminated执行");
    }

    @Override
    public void processDialogTerminated(DialogTerminatedEvent dialogTerminatedEvent) {
        log.info("processDialogTerminated执行");
    }

}
