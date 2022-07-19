clear all;
clc;
filename='D:\Desktop\分布式任务\ARIMA时间序列预测结果图\transaction_data.csv';
Y=csvread(filename,1,1,[1,1,27,1]);
Y=ceil(Y);
Y=Y';
plot(Y)

%ACF和PACF图
figure
autocorr(Y)
figure
parcorr(Y)

%平滑性检验,yd1_h_adf =1，yd1_h_kpss =0，通过检验
y_h_adf = adftest(Y);
y_h_kpss = kpsstest(Y);

% 一阶差分，结果平稳。如果依旧不平稳的话，再次求差分，直至通过检验
Yd1 = diff(Y);
yd1_h_adf = adftest(Yd1);
yd1_h_kpss = kpsstest(Yd1);

%Yd2转换成列向量
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

%reshape 重构数组
LOGL = reshape(LOGL,16,1);
PQ = reshape(PQ,16,1);
[~,bic] = aicbic(LOGL,PQ+1,100);
a=reshape(bic,4,4);

% 找最佳lags值 x=2，y=1，即对应ARMA（2，1）模型
a_max=max(a(:));
[x,y]=find(a==min(a(:)));


Mdl = arima(x, 1, y);  %第二个变量值为1，即一阶差分
EstMdl = estimate(Mdl,Y);
[res,~,logL] = infer(EstMdl,Y);   %res即残差

stdr = res/sqrt(EstMdl.Variance);
figure('Name','残差检验')
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
%上图为残差检验的结果图。
% Standardized Residuals是查看残差是否接近正态分布，理想的残差要接近正态分布；
% ACF和PACF检验残差的自相关和偏自相关，理想的结果应该在图中不存在超出蓝线的点；
% 最后一张QQ图是检验残差是否接近正太分布的，理想的结果中蓝点应该靠近红线。

% Durbin-Watson 统计是计量经济学分析中最常用的自相关度量
diffRes0 = diff(res);  
SSE0 = res'*res;
DW0 = (diffRes0'*diffRes0)/SSE0; % Durbin-Watson statistic，
% 该值接近2，则可以认为序列不存在一阶相关性。

%% 5.预测
step = 3; % 预测步数为 3
[forData,YMSE] = forecast(EstMdl,step,'Y0',Y);   
lower = forData - 1.96*sqrt(YMSE); %95置信区间下限
upper = forData + 1.96*sqrt(YMSE); %95置信区间上限

plot(forData)
disp(forData)

figure()
plot(Y,'Color',[.7,.7,.7]);
hold on
h1 = plot(length(Y):length(Y)+step,[Y(end);lower],'r:','LineWidth',2);
plot(length(Y):length(Y)+step,[Y(end);upper],'r:','LineWidth',2)
h2 = plot(length(Y):length(Y)+step,[Y(end);forData],'k','LineWidth',2);
legend([h1 h2],'95% 置信区间','预测值',...
	     'Location','NorthWest')
title('Forecast')
hold off
