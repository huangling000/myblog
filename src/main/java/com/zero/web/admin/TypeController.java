package com.zero.web.admin;

import com.zero.po.Type;
import com.zero.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    //@PageableDefault:设置默认分页大小，排序方式等，Model ：前端展示模型
    public String types(@PageableDefault(size = 9,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable,
                        Model model){
        model.addAttribute("page",typeService.listType(pageable));//查询到一页的数据放到模型中
        return "admin/types";

    }

    @GetMapping("/types/input")
    public String input(Model model){
        //model保证前端type对象可以得到一个值，在校验时不会报错
        model.addAttribute("type",new Type());
        return "admin/types-input";
    }

    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("type",typeService.getType(id));
        return "admin/types-input";
    }



    //result可以获取校验结果
    /*@Valid注明需要校验的内容，BindingResult接收对实体类的校验结果,也可自定义错误信息;两者必须紧靠才可生效*/
    @PostMapping("/types")
    public String post(@Valid Type type, BindingResult result, RedirectAttributes attributes){
        Type type1 = typeService.getTypeByName(type.getName());
        if(type1 != null){
            result.rejectValue("name","nameError","不能添加重复的分类");
        }
        if(result.hasErrors()){
            return "admin/types-input";
        }
        Type t = typeService.saveType(type);
        if( t == null){
            attributes.addFlashAttribute("message","增加失败");
        }else{
            attributes.addFlashAttribute("message","增加成功");
        }
        //重定向返回页面
        return "redirect:/admin/types";
    }

    @PostMapping("/types/{id}")
    public String editPost(@Valid Type type, BindingResult result,@PathVariable Long id, RedirectAttributes attributes){
        Type type1 = typeService.getTypeByName(type.getName());
        if(type1 != null){
            result.rejectValue("name","nameError","不能添加重复的分类");
        }
        if(result.hasErrors()){
            return "admin/types-input";
        }
        Type t = typeService.updateType(id, type);
        if( t == null){
            attributes.addFlashAttribute("message","更新失败");
        }else{
            attributes.addFlashAttribute("message","更新成功");
        }
        //重定向返回页面
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        typeService.deletedType(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/types";
    }
}
