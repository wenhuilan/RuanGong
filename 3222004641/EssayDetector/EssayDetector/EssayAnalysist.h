#pragma once
#ifndef ESSAYANALYSIST_H
#define ESSAYANALYSIST_H
#include<string>
#include<vector>
#include<map>
#include<sstream>
#include<cmath>
#include<thread>
#include "jieba.hpp"
using namespace std;

static class EssayAnalysist
{
public:
	//分词函数
	static vector<string> CutText(const string& Text)
	{
		//初始化结巴分词器
		cppjieba::Jieba jieba(
			"C:/Users/25768/Desktop/EssayDetector/3122004856/dict/jieba.dict.utf8",
			"C:/Users/25768/Desktop/EssayDetector/3122004856/dict/hmm_model.utf8",
			"C:/Users/25768/Desktop/EssayDetector/3122004856/dict/user.dict.utf8",
			"C:/Users/25768/Desktop/EssayDetector/3122004856/dict/idf.utf8",
			"C:/Users/25768/Desktop/EssayDetector/3122004856/dict/stop_words.utf8");
		vector<string> words;
		jieba.Cut(Text, words, true);
		return words;
		
	}
	//构造词频向量
	static vector<double> BuildFrequencyVector(const vector<string>& tokens)
	{
		//词频向量
		map<string, double> FrequencyVector;
		for (const string& word : tokens) 
		{
			FrequencyVector[word]++;
		}
		//转换为只存int的向量
		std::vector<double> FrequencyValues;
		for (const auto& pair : FrequencyVector) 
		{
			FrequencyValues.push_back(pair.second);
		}
		return FrequencyValues;
	}

	// 将文本分词后的字符串向量转化为文档集合的二维字符串向量
	static vector<vector<string>> ConvertToDocuments(const vector<string>& tokens, const string& delimiter) 
	{
		vector<vector<string>> documents;
		vector<string> CurrentDocument;

		// 遍历分词结果
		for (const auto& token : tokens) 
		{
			if (token == delimiter)
			{ // 遇到文档边界
				if (!CurrentDocument.empty()) 
				{ // 如果当前文档不为空
					documents.push_back(CurrentDocument); // 将当前文档存储到文档集合中
					CurrentDocument.clear(); // 清空当前文档
				}
			}
			else 
			{ // 遇到分词结果
				CurrentDocument.push_back(token); // 将分词结果存储到当前文档中
			}
		}
		// 处理最后一个文档
		if (!CurrentDocument.empty()) 
		{
			documents.push_back(CurrentDocument);
		}
		return documents;
	}

	// 计算逆文档频率（IDF）
	static vector<double> CalculateIDF(const vector<vector<string>>& documents) 
	{
		map<string, double> IdfValues;
		int TotalDocuments = documents.size();
		// 统计包含每个单词的文档数量
		map<string, int> WordDocumentCount;
		for (const auto& document : documents) 
		{
			map<string, bool> UniqueWords;
			for (const auto& word : document) 
			{
				if (!UniqueWords[word]) 
				{
					WordDocumentCount[word]++;
					UniqueWords[word] = true;
				}
			}
		}
		// 计算每个单词的IDF值
		for (const auto& pair : WordDocumentCount) 
		{
			const string& word = pair.first;
			int DocumentCount = pair.second;
			double idf = log(static_cast<double>(TotalDocuments) / (DocumentCount + 1)); // 加1是为了防止除零错误
			IdfValues[word] = idf;
		}	
		vector<double> IdfVector;
		// 遍历IdfValues，将每个IDF值存储到vector中
		for (const auto& pair : IdfValues) 
		{
			IdfVector.push_back(pair.second);
		}
		return IdfVector;
	}

	//根据向量计算文本相似度，基于余弦相似度
	static double CaculateSimilarity(const vector<double>& Vec1, const vector<double>& Vec2)
	{
		size_t VecSize = std::min(Vec1.size(), Vec2.size());
		if (VecSize == 0) {
			return 0.0;
		}

		double DotProduct = 0.0;
		double MagVec1 = 0.0;
		double MagVec2 = 0.0;

		// 计算点积
		for (size_t i = 0; i < VecSize; ++i) {
			DotProduct += Vec1[i] * Vec2[i];
			MagVec1 += Vec1[i] * Vec1[i];
			MagVec2 += Vec2[i] * Vec2[i];
		}

		// 计算向量的模
		MagVec1 = sqrt(MagVec1);
		MagVec2 = sqrt(MagVec2);

		// 计算余弦相似度
		double similarity = DotProduct / (MagVec1 * MagVec2);

		return similarity;
	}

};

#endif // !ESSAYANALYSIST_H
