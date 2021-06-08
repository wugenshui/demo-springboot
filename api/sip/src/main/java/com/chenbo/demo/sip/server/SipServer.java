package com.github.wugenshui.sip.server;

/**
 * @author : chenbo
 * @date : 2020-07-02
 */

import com.github.wugenshui.sip.MD5Util;
import lombok.extern.slf4j.Slf4j;

import javax.sip.ClientTransaction;
import javax.sip.DialogTerminatedEvent;
import javax.sip.IOExceptionEvent;
import javax.sip.InvalidArgumentException;
import javax.sip.ListeningPoint;
import javax.sip.ObjectInUseException;
import javax.sip.PeerUnavailableException;
import javax.sip.RequestEvent;
import javax.sip.ResponseEvent;
import javax.sip.ServerTransaction;
import javax.sip.SipException;
import javax.sip.SipFactory;
import javax.sip.SipListener;
import javax.sip.SipProvider;
import javax.sip.SipStack;
import javax.sip.TimeoutEvent;
import javax.sip.TransactionTerminatedEvent;
import javax.sip.TransportNotSupportedException;
import javax.sip.address.Address;
import javax.sip.address.AddressFactory;
import javax.sip.address.SipURI;
import javax.sip.address.URI;
import javax.sip.header.AuthorizationHeader;
import javax.sip.header.CallIdHeader;
import javax.sip.header.ContactHeader;
import javax.sip.header.DateHeader;
import javax.sip.header.HeaderFactory;
import javax.sip.header.ToHeader;
import javax.sip.header.WWWAuthenticateHeader;
import javax.sip.message.MessageFactory;
import javax.sip.message.Request;
import javax.sip.message.Response;
import java.text.ParseException;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Set;
import java.util.TooManyListenersException;

/**
 * sip服务端
 *
 * @author : chenbo
 * @date : 2020-07-02
 */
@Slf4j
public class SipServer implements SipListener {

    private static final String IP = "192.168.186.209";
    SipStack sipStack = null;
    HeaderFactory headerFactory = null;
    AddressFactory addressFactory = null;
    MessageFactory messageFactory = null;
    SipProvider sipProvider = null;

    /**
     * 保存正在注册的用户，注册第一步的
     */
    private static Set<String> registeIds = new HashSet<>();

    /**
     * 保存当前注册的用户，注册成功的
     */
    private static Hashtable<String, URI> registedContactURI = new Hashtable<>();

    public static void main(String[] args) {
        SipServer sipServer = new SipServer();
        sipServer.init();
    }

    public void init() {
        Properties prop = new Properties();
        prop.setProperty("javax.sip.STACK_NAME", "teststackname");
        prop.setProperty("javax.sip.IP_ADDRESS", "127.0.0.1");
        prop.setProperty("javax.sip.OUTBOUND_PROXY", "127.0.0.1:8888/UDP");
        // You need 16 for logging traces. 32 for debug + traces.
        // Your code will limp at 32 but it is best for debugging.
        prop.setProperty("gov.nist.javax.sip.TRACE_LEVEL", "32");
        // 日志文件
        // prop.setProperty("gov.nist.javax.sip.DEBUG_LOG", "siptestdebug.txt"); 日志文件
        // prop.setProperty("gov.nist.javax.sip.SERVER_LOG", "siptestlog.txt"); 日志文件

        SipFactory sipFactory = SipFactory.getInstance();
        sipFactory.setPathName("gov.nist");
        try {
            sipStack = sipFactory.createSipStack(prop);
        } catch (PeerUnavailableException e) {
            e.printStackTrace();
        }

        try {
            headerFactory = sipFactory.createHeaderFactory();
            addressFactory = sipFactory.createAddressFactory();
            messageFactory = sipFactory.createMessageFactory();
            ListeningPoint listeningPoint = sipStack.createListeningPoint(IP, 5060, "udp");

            sipProvider = sipStack.createSipProvider(listeningPoint);
            sipProvider.addSipListener(this);
            log.info("服务启动完成。。。");
        } catch (TransportNotSupportedException e) {
            e.printStackTrace();
        } catch (InvalidArgumentException e) {
            e.printStackTrace();
        } catch (TooManyListenersException e) {
            e.printStackTrace();
        } catch (ObjectInUseException e) {
            e.printStackTrace();
        } catch (PeerUnavailableException e) {
            e.printStackTrace();
        }
    }


    /**
     * 处理客户端请求
     *
     * @param requestEvent
     */
    @Override
    public void processRequest(RequestEvent requestEvent) {
        Request request = requestEvent.getRequest();
        if (null == request) {
            log.info("收到的requestEvent.getRequest() is null.");
            return;
        }

        log.info(">>>>>收到的request内容是\n" + request);

        switch (request.getMethod().toUpperCase()) {
            case Request.INVITE:
                log.info("收到INVITE的请求");
                break;
            case Request.REGISTER:
                log.info("收到REGISTER的请求");
                doRegister(request, requestEvent);
                break;
            case Request.SUBSCRIBE:
                log.info("收到SUBSCRIBE的请求");
                break;
            case Request.ACK:
                log.info("收到ACK的请求");
                break;
            case Request.BYE:
                log.info("收到BYE的请求");
                break;
            case Request.CANCEL:
                log.info("收到CANCEL的请求");
                break;
            default:
                log.info("不处理的requestMethod：" + request.getMethod().toUpperCase());
        }
    }

    /**
     * 处理客户端相应
     *
     * @param responseEvent
     */
    @Override
    public void processResponse(ResponseEvent responseEvent) {
        Response response = responseEvent.getResponse();
        if (null == response) {
            log.info("response is null.");
            return;
        }
        log.info("收到的Response is :" + response);
        ClientTransaction clientTransaction = responseEvent.getClientTransaction();
        Request request = clientTransaction.getRequest();
        log.info("收到的Response for request:" + request);

        if (response.getStatusCode() == Response.TRYING) {
            log.info("收到的response is 100 TRYING");
            return;
        }
        switch (request.getMethod().toUpperCase()) {
            case Request.INVITE:
                log.info("收到INVITE的响应");
                break;
            case Request.BYE:
                log.info("收到BYE的响应");
                break;
            case Request.CANCEL:
                log.info("收到CANCEL的响应");
                break;
            default:
                log.info("不处理的response的请求类型：" + request.getMethod().toUpperCase());
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
        // TODO Auto-generated method stub
    }

    @Override
    public void processDialogTerminated(DialogTerminatedEvent dialogTerminatedEvent) {
        // TODO Auto-generated method stub
    }

    private void doRegister(Request request, RequestEvent requestEvent) {
        if (null == request || null == requestEvent) {
            log.info("无法处理REGISTER请求，request=" + request + ",event=" + requestEvent);
            return;
        }
        ServerTransaction serverTransactionId = requestEvent.getServerTransaction();
        try {
            Response response = null;

            ToHeader toHead = (ToHeader) request.getHeader(ToHeader.NAME);
            Address toAddress = toHead.getAddress();
            URI toURI = toAddress.getURI();
            SipURI sipURI_to = (SipURI) toURI;
            String toUserId = sipURI_to.getUser();
            log.info("注册的toUserId是" + toUserId);

            ContactHeader contactHeader = (ContactHeader) request.getHeader(ContactHeader.NAME);
            Address contactAddr = contactHeader.getAddress();
            URI contactURI = contactAddr.getURI();

            log.info("注册 from: " + toURI + " request str: " + contactURI);
            if (null == toUserId || "".equals(toUserId)) {
                log.info("无法识别的userId，不处理。");
                return;
            }
            int expires = request.getExpires().getExpires();
            // 如果expires不等于0,则为注册，否则为注销。
            if (expires != 0 || contactHeader.getExpires() != 0) {
                // 注册
                if (registedContactURI.containsKey(toUserId)) {
                    // 已经注册了
                    log.info("已经注册过了 user=" + toURI);
                } else {
                    // 不是注册成功状态
                    if (registeIds.contains(toUserId)) {
                        // 是第二次注册
                        log.info("第二次注册 register user=" + toURI);
                        // 验证AuthorizationHeader摘要认证信息
                        AuthorizationHeader authorizationHeader = (AuthorizationHeader) request.getHeader(AuthorizationHeader.NAME);
                        boolean authorizationResult = false;
                        // 验证
                        if (null != authorizationHeader) {
                            String username = authorizationHeader.getUsername();
                            String realm = authorizationHeader.getRealm();
                            String nonce = authorizationHeader.getNonce();
                            URI uri = authorizationHeader.getURI();
                            String res = authorizationHeader.getResponse();
                            String algorithm = authorizationHeader.getAlgorithm();
                            log.info("Authorization信息：username=" + username + ",realm=" + realm + ",nonce=" + nonce + ",uri=" + uri + ",response=" + res + ",algorithm=" + algorithm);
                            if (null == username || null == realm || null == nonce || null == uri || null == res || null == algorithm) {
                                log.info("Authorization信息不全，无法认证。");
                            } else {
                                // 比较Authorization信息正确性
                                String A1 = MD5Util.MD5(username + ":" + realm + ":12345678");
                                String A2 = MD5Util.MD5("REGISTER:sip:servername@" + IP + ":5060");
                                String resStr = MD5Util.MD5(A1 + ":" + nonce + ":" + A2);
                                if (resStr.equals(res)) {
                                    //注册成功，标记true
                                    authorizationResult = true;
                                }
                            }
                        }
                        // 不管第二次是否成功都移除，失败要从头再来
                        registeIds.remove(toUserId);
                        // 验证成功加入成功注册列表，失败不加入
                        if (authorizationResult) {
                            // 注册成功
                            log.info("注册成功？");
                            registedContactURI.put(toUserId, contactURI);
                            // 返回成功
                            response = messageFactory.createResponse(Response.OK, request);
                            DateHeader dateHeader = headerFactory.createDateHeader(Calendar.getInstance());
                            response.addHeader(dateHeader);
                            log.info("返回注册结果 response是\n" + response.toString());

                            if (serverTransactionId == null) {
                                serverTransactionId = sipProvider.getNewServerTransaction(request);
                                serverTransactionId.sendResponse(response);
                                // serverTransactionId.terminate();
                                log.info("register serverTransaction: " + serverTransactionId);
                            } else {
                                log.info("processRequest serverTransactionId is null.");
                            }
                        } else {
                            // 注册失败
                            log.info("注册失败？");
                            // 返回失败
                            response = messageFactory.createResponse(Response.FORBIDDEN, request);
                            log.info("返回注册结果 response是\n" + response.toString());

                            if (serverTransactionId == null) {
                                serverTransactionId = sipProvider.getNewServerTransaction(request);
                                serverTransactionId.sendResponse(response);
                            } else {
                                log.info("processRequest serverTransactionId is null.");
                            }
                        }
                    } else {
                        // 是第一次注册
                        log.info("首次注册 user=" + toURI);
                        // 加入registing列表
                        registeIds.add(toUserId);
                        // 发送响应
                        response = messageFactory.createResponse(Response.UNAUTHORIZED, request);
                        String realm = "zectec";
                        CallIdHeader callIdHeader = (CallIdHeader) request.getHeader(CallIdHeader.NAME);
                        String callId = callIdHeader.getCallId();
                        String nonce = MD5Util.MD5(callId + toUserId);
                        WWWAuthenticateHeader wwwAuthenticateHeader = headerFactory.createWWWAuthenticateHeader("Digest realm=\"" + realm + "\",nonce=\"" + nonce + "\"");
                        response.setHeader(wwwAuthenticateHeader);
                        log.info("返回注册结果 response是\n" + response.toString());

                        if (serverTransactionId == null) {
                            serverTransactionId = sipProvider.getNewServerTransaction(request);
                            serverTransactionId.sendResponse(response);
                            // serverTransactionId.terminate();
                            log.info("register serverTransaction: " + serverTransactionId);
                        } else {
                            log.info("processRequest serverTransactionId is null.");
                        }

                    }
                }
            } else {
                // 注销
                log.info("注销 user=" + toURI);
                // 发送ok响应
                response = messageFactory.createResponse(Response.OK, request);
                log.info("返回注销结果 response  : " + response.toString());
                if (serverTransactionId == null) {
                    serverTransactionId = sipProvider.getNewServerTransaction(request);
                    serverTransactionId.sendResponse(response);
                    // serverTransactionId.terminate();
                    log.info("register serverTransaction: " + serverTransactionId);
                } else {
                    log.info("processRequest serverTransactionId is null.");
                }
                // 移除
                registeIds.remove(toUserId);
                registedContactURI.remove(toUserId);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (SipException e) {
            e.printStackTrace();
        } catch (InvalidArgumentException e) {
            e.printStackTrace();
        }
    }

}
