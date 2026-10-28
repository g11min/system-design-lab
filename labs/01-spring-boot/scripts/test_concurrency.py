from concurrent.futures import ThreadPoolExecutor
from urllib.request import urlopen

URL = "http://localhost:8080/api/projects/count"
TOTAL = 1000


def send_request(_):
    with urlopen(URL, timeout=10) as response:
        return int(response.read().decode())


with ThreadPoolExecutor(max_workers=50) as pool:
    results = list(pool.map(send_request, range(TOTAL)))

print("请求总数:", TOTAL)
print("返回值最大值:", max(results))
print("不同返回值数量:", len(set(results)))
