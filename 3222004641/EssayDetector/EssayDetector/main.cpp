
#include "FileManager.h"
#include "EssayAnalysist.h"
#include "Tester.h"
using namespace std;

int main(int argc, char *argv[])
{	 	
	for (int i= 0;i<argc;i++)
	{
		if (argc == 1)
			return 0;
		cout << "argv["<<i<<"]:" << argv[i] << endl;

	}
	//读取文章并转为string
	string OriginEssay = FileManager::ReadTextAndConvertToString(argv[argc-3]);
	string CopyEssay = FileManager::ReadTextAndConvertToString(argv[argc-2]);
	//分词
	vector<string> CutFromOrigin, CutFromCopy;
	CutFromOrigin = EssayAnalysist::CutText(OriginEssay);
	CutFromCopy = EssayAnalysist::CutText(CopyEssay);
	vector<vector<string>> DocOrig, DocCopy;
	DocOrig = EssayAnalysist::ConvertToDocuments(CutFromOrigin, " ");
	DocCopy = EssayAnalysist::ConvertToDocuments(CutFromCopy, " ");
	//计算IDF向量
	vector<double> IDFOfOrigin, IDFOfCopy;
	IDFOfOrigin = EssayAnalysist::CalculateIDF(DocOrig);
	IDFOfCopy = EssayAnalysist::CalculateIDF(DocCopy);
	//计算相似度并写入结果文件
	double DetectResult = EssayAnalysist::CaculateSimilarity(IDFOfOrigin, IDFOfCopy);	
	string ResultReport = "文章的相似度为:"+ to_string(DetectResult);
	cout << ResultReport << endl;	
	FileManager::WriteAnswerIntoFile(ResultReport, argv[argc-1]);

	////测试代码
	////样例1
	//string TestOrig1 = "今天是星期天，天气晴，今天晚上我要去看电影。";
	//string TestCopy1 = "今天是周天，天气晴朗，我晚上要去看电影。";	
	////样例2
	//string TestOrig2 = "《Starfield》是由载誉无数、曾为大家带来《The Elder Scrolls V: Skyrim》和《Fallout 4》的Bethesda Game Studios睽违25年所打造的第一个全新宇宙";
	//string TestCopy2 = "PC 版Grand Theft Auto V 能够以超越 4K 的最高分辨率和 60 帧每秒的帧率，为您呈现屡获殊荣、令人痴迷的游戏世界——洛桑托斯市和布雷恩郡";
	////样例3
	//string TestOrig3 = "掸掸喷灯和锤子上的灰尘 - 有新的工作给你，该去干活儿了。来到新的海岸边，这里有世界上数一数二的、超大型的船舶墓地，拆解掉大型的船舶部件，把成吨的价值不菲的钢铁资源都收集起来。撸起袖子加把劲儿，回到你的商业帝国来吧。";
	//string TestCopy3 = "不经历艰险，怎能成就非凡旅程。在《Outward》中，极冷的夜晚和感染的伤口与潜伏在黑暗中的捕食者一样危险。玩家可以选择单枪匹马，或是和他人合作，探索欧鲁拉的广袤世界。Definitive Edition包含了两个DLC和对生命质量的优化。";
	////样例4
	//string TestOrig4 = "《天国：拯救》是一个能让你沉浸于在神圣罗马帝国的史诗冒险的剧情向、开放世界角色扮演游戏。与入侵的敌人战斗以为你死去的双亲报仇。完成能改变剧情走向的任务，并做好每个能影响剧情的选择。";
	//string TestCopy4 = "艾尔登法环是以正统黑暗奇幻世界为舞台的动作RPG游戏。 走进辽阔的场景与地下迷宫探索未知，挑战困难重重的险境，享受克服困境时的成就感吧。 不仅如此，登场角色之间的利害关系谱成的群像剧，更是不容错过。";
	////样例5
	//string TestOrig5 = "我们将卡牌游戏与Roguelike游戏融为一体，制作出了这款最棒的单机组牌游戏。打造出独一无二的牌组、遇见无数离奇的生物、发现威力强大的遗物、去屠戮这座高塔吧！";
	//string TestCopy5 = "你被派到了一座孤岛上，寻找一位失踪的亿万富翁，结果却发现自己深陷被食人生物占领的炼狱之地。你需要制作工具和武器、建造房屋，倾尽全力生存下去，无论独自一人还是与朋友一起——一切尽在这款新推出的开放世界恐怖生存模拟游戏中。";
	////样例6
	//string TestOrig6 = "Red Dead Redemption 2 已荣获超过 175 项年度游戏奖项且获得超过 250 个满分评价，游戏中述说亚瑟·摩根和声名狼藉的范德林德帮派的传奇故事，体验在 19 世纪的最后岁月里横跨美国的亡命之旅。除此之外，还可免费享受 Red Dead 在线模式中与众多玩家共享的逼真世界。";
	//string TestCopy6 = "《The Outer Worlds》是一款荣获奖项的单人第一人称科幻RPG游戏，由Obsidian Entertainment和Private Division联手打造。当你探索一个太空殖民地时，你所选定的角色将决定这个玩家驱动的故事接下来将会如何展开。在这个殖民地的企业方程中，你是计划外的未知变量。";
	////样例7
	//string TestOrig7 = "知名游戏制作人小岛秀夫为大家带来了一种颠覆传统的体验，现在这种体验在此导演剪辑版的最终版本中加以扩展。玩家将扮演山姆·布里奇斯（Sam Bridges），你的任务就是将这残破的美国中最后的幸存者们联合起来，为人类带来希望。你能一步一步地将这破碎的世界重新连接起来吗？";
	//string TestCopy7 = "在世界上最盛大的汽车嘉年华上，不断变化的赛季改变着一切。单枪匹马或者与其他人组队，在一个共享开放世界中探索美丽而富有历史气息的英国。收集、改装和驾驶超过 450 辆车辆。尽情竞速、表演特技、创造和探索：选择您专属的方式来成为地平线中的超级巨星。";
	////样例8
	//string TestOrig8 = "Valve 携 VR 大作《半衰期：爱莉克斯》重磅回归《半衰期》系列。 这个战斗故事发生在《半衰期》和《半衰期2》之间，是与邪恶的外星种族联合军之间看似毫无胜算的较量。 扮演爱莉克斯·凡斯，人类存活下去的唯一希望。";
	//string TestCopy8 = "新的生命之地。狩猎, 就是本能! 「Monster Hunter: World」中,玩家可以体验终极的狩猎生活,活用新建构的世界中各种各样的地形与生态环境享受狩猎的惊喜与兴奋。";
	////样例9
	//string TestOrig9 = "在充满异域风情的广大世界中开启全新旅程，征服强大的敌人，解开极具挑战性的难题，在一次次任务中，揭开奥里的命运。";
	//string TestCopy9 = "在广阔的世界中收集神奇的生物“帕鲁”，派他们进行战斗、建造、做农活，工业生产等，这是一款支持多人游戏模式的全新开放世界生存制作游戏。";
	////样例10	
	//string TestOrig10 = "您的“终极地平线冒险”即将开启！驾驶世界名车，探索墨西哥充满活力的户外景色，享受无拘无束又充满乐趣的驾驶体验。在终极地平线拉力赛中征服富有挑战的塞拉努埃瓦。需拥有《极限竞速：地平线 5》才能体验，扩充内容需另购。";
	//string TestCopy10 = "与逾2200万名玩家一同游玩这款屡获殊荣的在线多人角色扮演游戏，在历久弥新的上古卷轴世界体验无限冒险。战斗、制造、争夺或探索，并结合不同种类的装备和能力，打造你个人专属的游戏风格。游戏无须购买订阅服务即可畅玩。";


	//Tester::TestForEssayAnalysist(TestOrig1, TestCopy1);
	//Tester::TestForEssayAnalysist(TestOrig2, TestCopy2);
	//Tester::TestForEssayAnalysist(TestOrig3, TestCopy3);
	//Tester::TestForEssayAnalysist(TestOrig4, TestCopy4);
	//Tester::TestForEssayAnalysist(TestOrig5, TestCopy5);
	//Tester::TestForEssayAnalysist(TestOrig6, TestCopy6);
	//Tester::TestForEssayAnalysist(TestOrig7, TestCopy7);
	//Tester::TestForEssayAnalysist(TestOrig8, TestCopy8);
	//Tester::TestForEssayAnalysist(TestOrig9, TestCopy9);
	//Tester::TestForEssayAnalysist(TestOrig10, TestCopy10);

	//return 0;
}