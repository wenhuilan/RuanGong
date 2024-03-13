#pragma once
#include "EssayAnalysist.h"
#include "FileManager.h"
#include <iostream>

using namespace std;

static class Tester
{
public:
	static void TestForFileManager(const string& ReadPath, const string& WritePath)
	{
		string OriginEssay = FileManager::ReadTextAndConvertToString(ReadPath);
		if (!OriginEssay.empty())
			printf("读取成功!");
		string Answer = "相似度为99.9%";
		FileManager::WriteAnswerIntoFile(Answer, WritePath);
	}

	static void TestForEssayAnalysist(const string& EssayOrig, const string& EssayCopy)
	{		
		vector<string> TestStrVec, TestStr2Vec;
		TestStrVec = EssayAnalysist::CutText(EssayOrig);
		TestStr2Vec = EssayAnalysist::CutText(EssayCopy);
		vector<double> TestVec1, TestVec2;				
		vector<vector<string>> d1, d2;
		d1 = EssayAnalysist::ConvertToDocuments(TestStrVec, " ");
		d2 = EssayAnalysist::ConvertToDocuments(TestStr2Vec, " ");
		TestVec1 = EssayAnalysist::CalculateIDF(d1);
		TestVec2 = EssayAnalysist::CalculateIDF(d2);
		double result = EssayAnalysist::CaculateSimilarity(TestVec1, TestVec2);
		printf("the result sim is : %3.2f\n", result);
	}

	static void ShowCutResult(const vector<string>& Word)
	{
		for (auto& str : Word)
		{
			cout << str << " ";
		}
	}

	static void ShowIDFVector(const vector<double>& VecOrig,const vector<double>& VecCopy)
	{
		printf("\n原文IDF向量:<");
		for (auto& num : VecOrig)
		{
			printf("%.2f, ", num);
		}
		printf(">\n");

		printf("抄袭文IDF向量:<");
		for (auto& num : VecCopy)
		{
			printf("%.2f, ", num);
		}
		printf(">\n");
	}
};