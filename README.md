```bash
export SA_NAME=service-account-with-permission-to-list-pods
export OVERRIDES="{\"apiVersion\":\"v1\",\"kind\":\"Pod\",\"spec\":{\"serviceAccountName\":\"${SA_NAME}\",\"securityContext\":{\"fsGroup\":65534}}}"
kubectl run kubernetes-client-test --rm -it \
    --image yurique/kubernetes-client-test:0.1.0-dec4f7f617aca6131f27a35e7e879b3f4e1a969b \
    --overrides=$OVERRIDES \
    -- \
    pods
```