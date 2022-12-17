```bash
export SA_NAME=service-account-with-permission-to-list-pods
export OVERRIDES="{\"apiVersion\":\"v1\",\"kind\":\"Pod\",\"spec\":{\"serviceAccountName\":\"${SA_NAME}\",\"securityContext\":{\"fsGroup\":65534}}}"
kubectl run kubernetes-client-test --rm -it \
    --image yurique/kubernetes-client-test:0.1.0-78f9028976e76f5f3f0088de56e88727523439f4 \
    --overrides=$OVERRIDES \
    -- \
    pods
```