# 授权客户端
INSERT INTO oauth_client_details
(client_id, resource_ids, client_secret, `scope`, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove)
VALUES('client', 'admin', '$2a$10$VmNaOAWKo2Ob9NYhh0.kLef8Z5ca8uvW3v1BfDE41LAOwROgWRG7q', 'all', 'authorization_code,password,client_credentials,implicit,refresh_token', 'https://www.baidu.com', 'ADMIN', 3600, 3600, NULL, NULL);
