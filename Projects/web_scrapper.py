import string
import requests
from bs4 import BeautifulSoup
import re

import os


def handle_requests_new(address):
    r = requests.get(address, headers={'Accept-Language': 'en-US,en;q=0.5'})
    return r


def update_filename_new(name):
    translator = str.maketrans(' ', '_', string.punctuation)
    name = name.translate(translator)
    name = name.replace("_—_", "_")
    name = name.replace("__", "_")
    name = name.strip() + ".txt"
    return name


def save_file_new(title_name, text):
    data = str.encode(text)
    f = open(title_name, "wb")
    f.write(data)
    f.close()
    return


def extract_all_articles_from_url(url, key_word):
    r = handle_requests_new(url)
    soup = BeautifulSoup(r.content, 'html.parser')
    articles = soup.find_all('article')

    for article in articles:

        article_type = article.find('span', class_="c-meta__type")
        ar_type = article.find('span', "data-test" == "article.type")

        if not ar_type:
            continue

        if article_type and article_type.text == key_word:
            url_article = article.find('a').get('href')
            article_web = handle_requests_new("https://www.nature.com" + url_article)

            soup_article = BeautifulSoup(article_web.content, 'html.parser')
            title_article = soup_article.title.text

            # print(title_article)

            body_article = soup_article.find("data-track-action" == 'view article')

            regex = re.compile(".*body.*")

            body_article = body_article.find('div', attrs={'class': regex})
            text_artcile = ""

            if body_article:
                text_article = body_article.text.strip()

            if text_article:
                filename_to_save = update_filename_new(title_article)
                save_file_new(filename_to_save, text_article)

                # print(filename_to_save)

limit = int(input())
topic = input()

all_root = os.getcwd()

for i in range(1, limit + 1):
    path = "Page_{}".format(i)

    os.chdir(all_root)
    os.mkdir(path)
    os.chdir(path)

    page = "https://www.nature.com/nature/articles?sort=PubDate&year=2020&page={}".format(i)

    print(page)

    extract_all_articles_from_url(page, topic)

    os.chdir(all_root)

print("Saved all articles")
