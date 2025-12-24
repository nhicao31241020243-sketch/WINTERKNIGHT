# A SIMPLE METROIDVANIA PLATFORMER 2D GAME: WINTER KNIGHT PROJECT
- MÃ LỚP HỌC PHẦN: `25C1INF50900702` 
- MÔN HỌC: `CẤU TRÚC DỮ LIỆU VÀ GIẢI THUẬT`
- TRƯỜNG: `ĐẠI HỌC KINH TẾ TP. HCM - UEH`
- GIÁO VIÊN HƯỚNG DẪN: `ĐẶNG NGỌC HOÀNG THÀNH`
## THÀNH VIÊN NHÓM
- `31241020243` Cao Hà Nhi ([@nhicao31241020243-sketch](https://github.com/nhicao31241020243-sketch))
- `31241027187` Nguyễn Đạt Phúc ([@datphuc0406-create](https://github.com/datphuc0406-create))
- `31241020009` Trần Nhựt Hào ([@haotran26](https://github.com/haotran26))
- `31231570386` Dương Băng Băng ([@Bang-bang2805](https://github.com/Bang-bang2805))

______

The idea of our game is inspired by the famous 2D platformer game Super Mario, in which players control a character to overcome obstacles, collect items, and defeat enemies throughout different levels.

The game is titled “Winter Knight” and is set in a winter-themed environment, which is suitable for the time when this project is carried out, as winter and Christmas are approaching. In the game, the player takes on the role of a UEH student who embarks on an adventure in a winter landscape, exploring various levels to search for treasures in order to pay off academic credit debts, while overcoming different challenges along the way.

The ultimate goal of the game is to defeat the final boss and complete the game. During gameplay, Christmas-themed items are randomly placed along the path to enhance the player’s abilities and increase the overall enjoyment and engagement of the game.

______
## GIỚI THIỆU ỨNG DỤNG
- *BẢN ĐỒ TRONG PHẦN MỀM ĐƯỢC THAM KHẢO TẠI: https://cdn.thuvienphapluat.vn/phap-luat/2022-2/NTTY/ban-do-34-tinh-thanh.pdf*
- Trong lĩnh vực vận tải, việc lựa chọn lộ trình hợp lý có ảnh hưởng trực tiếp đến chi phí di chuyển. 
Mục tiêu bài toán đặt ra là xác định lộ trình có tổng chi phí vận tải nhỏ nhất 
trong số các lộ trình khả thi. Vì vậy, đề tài này tập trung ứng dụng thuật toán Dijkstra để 
xác định tuyến đường có chi phí vận tải thấp nhất giữa hai địa điểm trong hệ thống giao 
thông dựa trên các yếu tố chính: 
## *HỆ THỐNG VẬN TẢI*
- Trong phần mềm bao gồm 8 tỉnh khác nhau của Việt Nam: TP.HCM, Đồng Nai, Tây Ninh, Vĩnh Long, Đồng Tháp, Cần Thơ, An Giang, Cà Mau.
## *CHI PHÍ*
- Hiệu suất nhiên liệu: 9 lít / 100 km
- Giá xăng hiện hành: 22,700 VNĐ/lít
- Vận tốc trung bình: 80 km/h 
- Chi phí = (Quãng đường/100) x 9 x 22.700
## *THỜI GIAN*
- Thời gian di chuyển trong đề tài phụ thuộc vào 2 yếu tố: 
- Quãng đường (km)
- Vận tốc trung bình: 80 km/h
- THỜI GIAN = S/V = QUÃNG ĐƯỜNG / 80
## *CẠNH (TUYẾN ĐƯỜNG)*
- Trong chương trình, đồ thị được biểu diễn bằng ma trận kề adj[,] kích thước 8×8, tương ứng với 8 địa điểm (đỉnh) trong hệ thống vận tải.
## *ĐỈNH (TỈNH, THÀNH)*
- Chương trình tạo các đỉnh của đồ thị dựa trên các địa điểm vận tải, trong đó thông tin đỉnh được quản lý bởi Class SetUpGraph, trạng thái thuật toán được lưu trong Class Vertex, dữ liệu địa điểm được biểu diễn qua Class Location và hiển thị vị trí bằng Class Point.

## *GIAO DIỆN PHẦN MỀM*
