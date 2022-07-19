clear all;
clc;
filename='D:\Desktop\�ֲ�ʽ����\ARIMAʱ������Ԥ����ͼ\transaction_data.csv';
Y=csvread(filename,1,1,[1,1,27,1]);
Y=ceil(Y);
Y=Y';
plot(Y)

%ACF��PACFͼ
figure
autocorr(Y)
figure
parcorr(Y)

%ƽ���Լ���,yd1_h_adf =1��yd1_h_kpss =0��ͨ������
y_h_adf = adftest(Y);
y_h_kpss = kpsstest(Y);

% һ�ײ�֣����ƽ�ȡ�������ɲ�ƽ�ȵĻ����ٴ����֣�ֱ��ͨ������
Yd1 = diff(Y);
yd1_h_adf = adftest(Yd1);
yd1_h_kpss = kpsstest(Yd1);

%Yd2ת����������
Yd1=Yd1';
Y=Y';

LOGL = zeros(4,4); % Initialize
PQ = zeros(4,4);
for p = 1:4
    for q = 1:4
        Mdl = arima(p,1,q);
        [~,~,logL] = estimate(Mdl,Yd1,'Display','off');
        LOGL(p,q) = logL;
        PQ(p,q) = p + q;
     end
end

%reshape �ع�����
LOGL = reshape(LOGL,16,1);
PQ = reshape(PQ,16,1);
[~,bic] = aicbic(LOGL,PQ+1,100);
a=reshape(bic,4,4);

% �����lagsֵ x=2��y=1������ӦARMA��2��1��ģ��
a_max=max(a(:));
[x,y]=find(a==min(a(:)));


Mdl = arima(x, 1, y);  %�ڶ�������ֵΪ1����һ�ײ��
EstMdl = estimate(Mdl,Y);
[res,~,logL] = infer(EstMdl,Y);   %res���в�

stdr = res/sqrt(EstMdl.Variance);
figure('Name','�в����')
subplot(2,3,1)
plot(stdr)
title('Standardized Residuals')
subplot(2,3,2)
histogram(stdr,10)
title('Standardized Residuals')
subplot(2,3,3)
autocorr(stdr)
subplot(2,3,4)
parcorr(stdr)
subplot(2,3,5)
qqplot(stdr)
%��ͼΪ�в����Ľ��ͼ��
% Standardized Residuals�ǲ鿴�в��Ƿ�ӽ���̬�ֲ�������Ĳв�Ҫ�ӽ���̬�ֲ���
% ACF��PACF����в������غ�ƫ����أ�����Ľ��Ӧ����ͼ�в����ڳ������ߵĵ㣻
% ���һ��QQͼ�Ǽ���в��Ƿ�ӽ���̫�ֲ��ģ�����Ľ��������Ӧ�ÿ������ߡ�

% Durbin-Watson ͳ���Ǽ�������ѧ��������õ�����ض���
diffRes0 = diff(res);  
SSE0 = res'*res;
DW0 = (diffRes0'*diffRes0)/SSE0; % Durbin-Watson statistic��
% ��ֵ�ӽ�2���������Ϊ���в�����һ������ԡ�

%% 5.Ԥ��
step = 3; % Ԥ�ⲽ��Ϊ 3
[forData,YMSE] = forecast(EstMdl,step,'Y0',Y);   
lower = forData - 1.96*sqrt(YMSE); %95������������
upper = forData + 1.96*sqrt(YMSE); %95������������

plot(forData)
disp(forData)

figure()
plot(Y,'Color',[.7,.7,.7]);
hold on
h1 = plot(length(Y):length(Y)+step,[Y(end);lower],'r:','LineWidth',2);
plot(length(Y):length(Y)+step,[Y(end);upper],'r:','LineWidth',2)
h2 = plot(length(Y):length(Y)+step,[Y(end);forData],'k','LineWidth',2);
legend([h1 h2],'95% ��������','Ԥ��ֵ',...
	     'Location','NorthWest')
title('Forecast')
hold off
