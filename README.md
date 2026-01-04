# 🚀 FlashSale-Engine (高并发秒杀系统实战)

![Java](https://img.shields.io/badge/Java-8-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.3.12.RELEASE-green)

## 📖 项目介绍 (Introduction)

本项目是一个**针对 Java 高频面试考点的实战演练场**。
项目模拟“高并发抢购/秒杀”场景，旨在解决“超卖”、“高并发”、“数据一致性”、“分布式锁”、“流量削峰”等核心技术难题。

作为一个全栈实战项目，本项目涵盖了从**需求分析、架构设计、代码开发、性能测试到 Docker 容器化部署**的全流程。

## 🎯 项目目标 (Goals)

- **核心业务**：实现用户登录、商品列表、商品详情、秒杀下单、订单查询。
- **技术深度**：
    - 彻底解决**超卖**问题（数据库锁 vs 分布式锁）。
    - 应对**高并发**流量（Redis 缓存 + RabbitMQ 异步削峰）。
    - 提升**系统稳定性**（Sentinel 限流与熔断）。
- **运维实战**：使用 Docker & Docker Compose 进行容器化编排，并在云服务器上进行生产环境部署。

## 🏗 技术栈 (Tech Stack)

### 后端 (Backend)
- **核心框架**: Spring Boot 2.3.12.RELEASE
- **持久层**: MyBatis-Plus
- **数据库**: MySQL 8.0
- **缓存**: Redis 7.x (缓存预热、Lua 脚本、分布式锁)
- **消息队列**: RabbitMQ (流量削峰、异步解耦)
- **安全**: Spring Security + JWT
- **工具**: Lombok, Hutool, Guava

### 运维 & 部署 (DevOps)
- **容器化**: Docker, Docker Compose
- **Web 服务器**: Nginx (反向代理、静态资源缓存)
- **CI/CD**: GitHub Actions (计划中)

## 🗺️ 开发路线图 (Roadmap)

### Phase 1: 基础设施与单体架构 (MVP)
- [ ] 初始化 Spring Boot 项目与 GitHub 仓库。
- [ ] 设计数据库表结构 (User, Goods, Order, SeckillGoods)。
- [ ] 实现基本的 CRUD 接口（商品展示、普通下单）。
- [ ] **痛点模拟**：使用 JMeter 压测，复现“超卖”现象。
- [ ] **解决方案**：引入 MySQL 悲观锁/乐观锁解决超卖。

### Phase 2: 高性能优化 (Cache & Optimization)
- [ ] 集成 Redis，实现用户 Token 管理与商品缓存。
- [ ] **页面静态化**：将商品详情页静态化，减少 DB 压力。
- [ ] **Redis 预减库存**：使用 Lua 脚本在 Redis 中原子扣减库存。
- [ ] **内存标记**：使用 Map 标记库存状态，减少 Redis 访问。

### Phase 3: 异步解耦与分布式 (Async & Distributed)
- [ ] 集成 RabbitMQ，将秒杀请求入队。
- [ ] 编写消费者服务，异步处理订单创建与数据库落库。
- [ ] 集成 Redisson，实现分布式锁，替代数据库锁。

### Phase 4: 安全与稳定性 (Security & Stability)
- [ ] 隐藏秒杀地址（接口加密/动态 URL）。
- [ ] 引入 Sentinel 进行接口限流与熔断降级。
- [ ] 图形验证码校验（防止脚本刷单）。

### Phase 5: 云端部署 (Deployment)
- [ ] 编写 `Dockerfile` 和 `docker-compose.yml`。
- [ ] 购买云服务器，配置 Docker 环境。
- [ ] 域名解析与 Nginx 配置 HTTPS。
- [ ] 线上压测与监控。

## 📂 目录结构 (Project Structure)

```text
flashsale-engine/
├── src/
│   ├── main/
│   │   ├── java/com/example/flashsale/
│   │   │   ├── config/      # 配置类 (Redis, MVC, RabbitMQ)
│   │   │   ├── controller/  # 控制层
│   │   │   ├── service/     # 业务逻辑层 (核心)
│   │   │   ├── mapper/      # 数据库访问层
│   │   │   ├── entity/      # 数据库实体
│   │   │   ├── vo/          # 视图对象
│   │   │   ├── exception/   # 全局异常处理
│   │   │   └── utils/       # 工具类 (MD5, UUID, Validator)
│   │   └── resources/
│   │       ├── mapper/      # MyBatis XML
│   │       ├── application.yml
│   │       └── sql/         # 初始化 SQL 脚本
├── deploy/                  # 部署相关 (Docker, Nginx Conf)
├── doc/                     # 项目文档与压测报告
├── pom.xml
└── README.md