#pragma once
#ifndef FILEMANAGER_H
#define FILEMANAGER_H
#include <string>
#include <fstream>
#include <sstream>
#include <iostream>
using namespace std;

static class FileManager
{
public:
	static string ReadTextAndConvertToString(const string& FilePath)
	{
        string Content;
        ifstream File(FilePath);
        if (!File.is_open()) 
        {
            cerr << "无法打开文件：" << FilePath << endl;
            return "";
        }
        string Line;
        while (std::getline(File, Line)) 
        {
            Content += Line + "\n"; 
        }
        File.close();
        return Content;
	}

    static void WriteAnswerIntoFile(const string& Ans, const string& FilePath)
    {
        std::ofstream File(FilePath, std::ofstream::out | std::ofstream::trunc); // 打开文件时清空文件
        if (!File.is_open()) 
        {
            std::cerr << "无法打开文件：" << FilePath << std::endl;
            return;
        }
        File << Ans;
        File.close();
        std::cout << "文件写入成功！" << std::endl;
    }
};
#endif // !FILEREADER_H